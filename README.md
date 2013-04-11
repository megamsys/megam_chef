megam_chef
==========

Java based API which accepts a JSON request, builds a chef command line using (Knife).

### Requirements

> [Opensource Chef Server 10 +](http://docs.opscode.com/chef_overview_server_open_source.html)
> [Chef workstation](http://docs.opscode.com/install_workstation.html)
> [OpenJDK 7.0](http://openjdk.java.net/install/index.html)
> [* optional - Riak - 1.3.1 +](http://docs.basho.com/riak/latest/tutorials/installation/Installing-on-Debian-and-Ubuntu/)  
> [* optional - Erlang R15B01]

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

import ?

ChefServiceRunner csc = (new ChefServiceRunner().with(CHEF_WITH_SHELL).input(__);

```
### Input JSON

```json

```

`field 1:` <yes/no> : yes => means there is a datasource that megam_chef should use. The supported datasource is Riak.
Postgresql support is a work under progress.

`field 2:` <development, production, staging, test ..> : The value that is entered needs to have a matching section with the 
same name. For instance if the config is `development` then a section following it needs to have the values for it.



### Use case : source = no

This attaches a NoneSource, and expects the input to be a JSON.


### Use case : source = yes

This attaches the source as per the naming convention. If `riak` is present, then a RiakSource is attached.
Basho's `riak-java-client` is used to interface with Riak.

#### Purpose of a data source

We have intermediatories which would intercept a request validate the shared HMAC of a requestor and stored the 
request using an `id` in a datastore. We choose `Riak`. 

   
### Running the application

Just the sample class SampleChefRunner as illustrated above.
 
Refer the example java class for more information. 

We are glad to help if you have questions.

[twitter](http://twitter.com/indykish) [email](rajthilak@megam.co.in)

#### TO - DO

* Postgresql support
* cancellable/Stoppable actions
* interface to [megam_play](https://github.com/indykish/megam_play) 
	
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

