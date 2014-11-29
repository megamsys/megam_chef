==================================================================================================================
This code is DEPRECATED. We built a golang (golang.org) based system https://github.com/megamsys/megamd.git. 
==================================================================================================================



megam_chef
==========

Scala/Java based Opscode Chef connector. This API accepts a request in JSON format, builds a chef command line using (Knife) and
executes them. There is an option to store the json in a data-source (Riak supported currently) and the
program retrieves it and executes them.

*  Snapshots are available in mvn, sbt

### Requirements

>
[Opensource Chef Server 10 +](http://docs.opscode.com/chef_overview_server_open_source.html)
[Chef workstation](http://docs.opscode.com/install_workstation.html)
[OpenJDK 7.0](http://openjdk.java.net/install/index.html)
[* optional - Riak - 1.3.1 +](http://docs.basho.com/riak/latest/tutorials/installation/Installing-on-Debian-and-Ubuntu/)  
[* optional - Erlang R15B01]

#### Tested on Ubuntu 12.10, 13.04, AWS - EC2

## Usage

### Scala


> Add this dependency in `build.sbt`

```xml
resolvers  +=  "Sonatype OSS Snapshots"  at  "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.github.indykish" % "megam-chef" % "1.0.0-BUILD-SNAPSHOT"

```


### Java


> Add this maven dependency in the `pom.xml`

```xml
	<dependency>
	<groupId>com.github.indykish</groupId>
	<artifactId>megam-chef</artifactId>
	<version>0.1.0-SNAPSHOT</version>
	</dependency>
```

### Preparing your program

#### Pre-requisites

Before your run it,

* Opensource Chef 11 (or) Hosted Chef running
* Required Cookbooks, Runlist uploaded to the Chef Server
* [optional][Cloud Bridge - megam_akka](https://github.com/indykish/megam_akka.git) running
* A chef client created, and knife command works as intended.
* [optional] - Riak running, and has the bucket named as configured in chefapp.yaml file.

### Configuring `chefapp.yaml`

There exists a default configuration file `chefapp.yaml` file which can be configured as intended.

Let us say your `ENV[HOME]` is `/home/ram` as is represented as ~ below.

When megam_chef is run inside your java (or) scala (or) akka/play .. program, it verifies if a file
exists `var/lib/megam/megamd/chefapp.yaml`.

If this is the first run, then it creates the default file as shown below at `~/.megam/chefapp.yaml`

The default contents of `~/.megam/chefapp.yaml` is as follows:

```
megamchef:
        config: 'development'
development:
      source: 'riak'
      host: 'localhost'
      port: '8098'
      bucket: 'requests'
production:
      source: 'riak'
      host: 'riak.megam.co'
      port: '8098'
      bucket: 'requests'
```
#### Configuration details:

`config:` <development, production, staging, test> : The value that is entered needs to have a matching section with the
same name. For instance if the config is `development` then a section following it needs to have the values for it.

`development, production, staging, test` are the only supported values.

`source:` <yes/no> : yes => means there is a datasource that megam_chef should use. The supported datasource is Riak.
Postgresql support is a work under progress.
				   : no => no datasource in use (or) none source

`host:`            : hostname of riak datasource

`port:`            : port  of riak datasource

`bucket:`          : bucket to pull from riak datasource

You have noticed above that by default source is `no`, and hence `no data source` is needed to work with this API.

#### Why a data source ?

We have intermediatories[megam_play](https://github.com/indykish/megam_play) which would intercept an RESTful request validate
the shared HMAC of a requestor and store the JSON request using an `id` in a datastore.

We also have a [megam_akka](https://github.com/indykish/megam_akka) connected to [RabbitMQ](http:\\rabbitmq.com) subscribed to all
the validated requests. Its job is to get those requests and run them.

Supported data-store is `Riak`.


### Use case : source = yes

This attaches the source as per the naming convention. If `riak` is present, then a RiakSource is attached.
Basho's `riak-java-client` is used to interface with Riak.

### Use case : source = no

This attaches a NoneSource, and expects the input to be proived as a JSON.

The ChefServiceRunner is the executioner for megam_chef.


### Input JSON

```json
{
	"systemprovider": {
		"provider": {
			"prov": "chef"
		}
	},
	"compute": {
		"cctype": "ec2",
		"cc": {
			"groups": "megam",
			"image": "ami-56e6a404",
			"flavor": "m1.small"
		},
		"access": {
			"ssh-key": "megam_ec2"
			"identity-file": "~/.ssh/megam_ec2.pem",
			"ssh-user": "ubuntu"
		}
	},
	"cloudtool": {
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

#### cc
`cctype:` <ec2, rackspace, openstack, ..> : The cloud to go after.This field is primed by [megam_akka](https://github.com/indykish/megam_play)  

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



### Setting up megam_chef with inteface to Riak

`$ riak start		   //start riak`

`$ ps aux | grep riak* //verify riak is running`

` riak-admin test      //test riak`

` curl -v http://localhost:8098/riak/requests //create a bucket requests`

` //Drop a json with id=RIP00902920202 into riak bucket requestes
  curl -v -XPUT -d '{"systemprovider": {"provider": {"prov": "chef"}}, "compute": { "cctype":"ec2","cc": {"groups": "megam","image": "ami-56e6a404","flavor": "m1.small"},"access": {"ssh-key":"megam_ec2","identity-file": "~/.ssh/megam_ec2.pem","ssh-user": "ubuntu"}}, "chefservice": {"chef": {"command": "knife","plugin": "ec2 server create",
  "run-list": "'role[opendj]'","name": "-N TestOverAll"}} }' -H "Content-Type: application/json" -H "X-Riak-Vclock: a85hYGBgzGDKBVIszMk55zKYEhnzWBlKIniO8mUBAA==" http://localhost:8098/riak/requests/RIP00902920202`


```java

import org.megam.chef.ChefServiceRunner;
import org.megam.chef.DropIn;
import org.megam.chef.ProvisionerFactory.TYPE;
import org.megam.chef.exception.BootStrapChefException;
import org.megam.chef.exception.ProvisionerException;
import org.megam.chef.exception.SourceException;

		//the id "sample" will be used to lookup riak for the json that megam_chef can execute.

		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn("sample")).control();


```
### Setting up megam_chef with `none`

Tweak the `json` as per your need. Store it in a know path and execute the below code. In here
we have illustrated executing  `conf\foo.json`


```java

		// load a sample json as seen from the above example from the conf directory
        List<String> allLines = File.readAllLines(FileSystems.getDefault().getPath("conf", "foo.json"));

		String jsonString = allLines.toString().trim();

		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn(jsonString)).control();


```


### Validation

When `ChefServiceRunner` executes, the following validation happens

* The hooked up provider (Let us say DefaultProvisionerChefWithShell) is the default one. If that is the case
then the command knife -version is run before the run list can be executed.  If the validation conditions fail,
then the follow reason appears.

```
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
The above error means the runlist value in your input JSON is wrong. Runlist must contain following value `'role[--receipe name--]'`.  


### Testing your application

Just see the sample class as illustrated in the `src/test/java` package.

```java
		//this is with source attached.

		(new ChefServiceRunner()).with(TYPE.CHEF_WITH_SHELL).input(new DropIn("sample")).control();

```

We are glad to help if you have questions, or request for new features..

[twitter](http://twitter.com/indykish) [email](<rajthilak@megam.co.in>)

#### TO - DO

* Unique process output files, right now this just dumps it to a file named `kh`
* Streams(scala) to move around the process out.
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
