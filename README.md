megam_chef
==========

Java based Opscode Chef connector. The API accepts a JSON request, builds a chef command line using (Knife).

### Requirements

> 
[Opensource Chef Server 10 +](http://docs.opscode.com/chef_overview_server_open_source.html)
[Chef workstation](http://docs.opscode.com/install_workstation.html)
[OpenJDK 7.0](http://openjdk.java.net/install/index.html)
[* optional - Riak - 1.3.1 +](http://docs.basho.com/riak/latest/tutorials/installation/Installing-on-Debian-and-Ubuntu/)  
[* optional - Erlang R15B01]

#### Tested on Ubuntu 12.10, 13.04, AWS - EC2

## Usage

Let us say your `ENV[HOME]` is `/home/ram`. When megam_chef is runs inside your java (or) scala (or) scala/akka/play .. program, 
it verifies if a file named exists `~/.megam/chefapp.yaml`.

If this is the initial run, then it creates the default file as shown below at `~/.megam/chefapp.yaml` 

The default contents of `~/.megam/chefapp.yaml` is as follows:

```
megamchef: 
        config: 'development'
        source: no
development: 
      source: 'riak'
      host: 'localhost'
      port: '8098'
      bucket: 'megam_prov'
production: 
      source: 'riak'
      host: 'riak.megam.co'
      port: '8098'
      bucket: 'megam_prov'      
```
###

`source:` <yes/no> : yes => means there is a datasource that megam_chef should use. The supported datasource is Riak.
Postgresql support is a work under progress.
				   : no => no datasource in use (or) none source	

`config:` <development, production, staging, test ..> : The value that is entered needs to have a matching section with the 
same name. For instance if the config is `development` then a section following it needs to have the values for it.

You have noticed above that by default source is `no`, and hence `no data source` is needed to work with this API. 

### Prepare your program

Before your run it,

* Opensource Chef Server (or) Hosted Chef running
* Required Cookbooks, Runlist uploaded to the Chef Server
* A chef client created, and knife command works as intended.
* optional - Riak running, and has the bucket named as configured in yaml file.


> Add this maven dependency

```xml
	<dependency>
	<groupId>org.megam</groupId>
	<artifactId>chef</artifactId>
	<version>0.1</version>
	</dependency>
```

> Invoking megam_chef

```java

import org.megam.chef.ChefServiceRunner;
import org.megam.chef.DropIn;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;

ChefServiceRunner csc = (new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn("sample")).control();


```
The ChefServiceRunner is executioner for megam_chef api.

### Input JSON

```json
{
	"systemprovider": {
		"provider": {
			"prov": "chef"
		}
	},
	"compute": {
		"ec2": {
			"groups": "megam",
			"image": "ami-56e6a404",
			"flavor": "m1.small"
		},
		"access": {
			"ssh-key": "megam_ec2",
			"identity-file": "~/.ssh/megam_ec2.pem",
			"ssh-user": "ubuntu"
		}
	},
	"chefservice": {
		"chef": {
			"command": "knife",
			"plugin": "ec2 server create",
			"run-list": "'role[opendj]'",
			"name": "-N TestOverAll"
		}
	}
}

```
#### provider
`prov:` <chef/none> : chef => means systems and cloud infrastructure automation framework that makes it easy to deploy servers and applications to any physical, virtual, or cloud location, no matter the size of the infrastructure. 
For more detail about `chef` visit `http://docs.opscode.com/#getting-started`

#### ec2 
`groups:` <development, production, staging, test ..> : A security group acts as a firewall that controls the traffic allowed to reach one or more instances. 

`image:` The name of the image that identifies the operating system (and version) that will be used to create the virtual machine.

`flavor:` The name of the flavor that identifies the hardware configuration of the server, including disk space, memory capacity, and CPU priority.

#### access 
`ssh-key:` The SSH key for the Amazon EC2 environment.

`identity-file:` The SSH identity file used for authentication. Key-based authentication is recommended.

`ssh-user:` The SSH user name for the Amazon EC2 environment.

#### chef
`command:` Knife is a command-line tool that provides an interface between a local Chef repository and the Chef Server.

`plugin:` The ec2 subcommand is used to manage API-driven cloud servers that are hosted by Amazon EC2. The server create argument is used to create a new Amazon EC2 cloud instance.

`run-list:` A comma-separated list of roles and/or recipes to be applied.

## Validation
The following validations happen 


* The hooked up provider (Let us say DefaultProvisionerChefWithShell) is the selected default one. If that is the case
then the command knife -version is run before the run list can be executed.  If the validation conditions fail,
then the follow reason appears.

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/home/rajthilak/code/megam/workspace/megam_chef/lib/slf4j-jdk14-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/rajthilak/.m2/repository/org/slf4j/slf4j-log4j12/1.7.5/slf4j-log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.JDK14LoggerFactory]
May 15, 2013 11:13:40 AM org.megam.chef.ChefServiceRunner with
INFO: Chef serProvisionerFactoryvice runner - started.
May 15, 2013 11:13:40 AM org.megam.chef.BootStrapChef bootedUp
INFO: ------------------------- MEGAM CHEF version : 0.1Build Date : 20130418----------------------
May 15, 2013 11:13:40 AM org.megam.chef.BootStrapChef configureRoot
INFO: /home/rajthilak/code/megam/workspace/megam_chef
May 15, 2013 11:13:40 AM org.megam.chef.BootStrapChef yamlSetup
INFO: /home/rajthilak
May 15, 2013 11:13:40 AM org.megam.chef.AppYamlLoader load
INFO: Yaml File Loaded

ERROR: groups key is missing 
ERROR: runlist key is not valid 

``` 
In this error means the runlist value in your input JSON is wrong. Runlist must contain following value `'role[--receipe name--]'`. The same checking executed at full of input JSON.  


### Use case : source = no

This attaches a NoneSource, and expects the input to be a JSON.

```java

		// load a sample json as seen from the above example from the conf directory		
        List<String> allLines = File.readAllLines(FileSystems.getDefault().getPath("conf", "foo.json"));
 
		String jsonString = allLines.toString().trim();
		
		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn(jsonString)).control();


```

### Use case : source = yes

This attaches the source as per the naming convention. If `riak` is present, then a RiakSource is attached.
Basho's `riak-java-client` is used to interface with Riak.

```java
		//the key "sample" will be used to lookup riak for the json that megam_chef can execute.
		
		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn("sample")).control();

```

#### Purpose of a data source

We have intermediatories[megam_play](https://github.com/indykish/megam_play) which would intercept a request validate the shared HMAC of a requestor and stored the 
request using an `id` in a datastore. We choose `Riak`. 

   
### Running the application

Just see the sample class as illustrated in the `src/test/java` package.

```java
		//this is with source attached.
		
		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn("sample")).control();

```
 
We are glad to help if you have questions, or request for new features..

[twitter](http://twitter.com/indykish) [email](<rajthilak@megam.co.in>)

#### TO - DO

* Stoppable actions
* Interface to [megam_akka](https://github.com/indykish/megam_play) 
* Pooled runners
* Postgresql support
	
# License


|                      |                                          |
|:---------------------|:-----------------------------------------|
| **Author:**          | Rajthilak (<rajthilak@megam.co.in>)
| **Copyright:**       | Copyright (c) 2012-2013 Megam Systems.
| **License:**         | Apache License, Version 2.0

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
