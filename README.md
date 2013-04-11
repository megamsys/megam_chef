megam_chef
==========

Java based API which accepts a JSON request, builds a chef command line using (Knife).

### Requirements

1. chef
2. Riak

### Installing chef

   You can install chef by running :
   
		$ gem install chef 
         
   you can also denote the version using the `-v version`
   
   After installing chef we are verify the chef by running :
   
   		$ gem list | grep chef

### Installing Riak

    Installing From Apt-Get

             If you wish to just install Riak and get on with your life, use apt-get.

             First you must get the signing key.

                     $ curl http://apt.basho.com/gpg/basho.apt.key | sudo apt-key add -

             Then add the Basho repository to your apt sources list (and update them).

                     $ sudo bash -c "echo deb http://apt.basho.com $(lsb_release -sc) main > /etc/apt/sources.list.d/basho.list"
                     $ sudo apt-get update

             Now install riak.

                     $ sudo apt-get install riak

             That should be all.
             
    Installing Riak From Source

             First, install Riak dependencies using apt:

                     $ sudo apt-get install build-essential libc6-dev-i386 git

             Riak requires `Erlang R15B01`. Note: don't use `Erlang version R15B02 or R15B03`, for the moment, as it causes an error with riak-admin status commands. If Erlang is not already installed, install it before continuing (see: Installing Erlang for more information).

             With Erlang installed, proceed to downloading and installing Riak:

             If the build was successful, a fresh build of Riak will exist in the rel/riak directory.
             
For more detail about `riak` visit `http://docs.basho.com/index.html`
     
### Running the application

		> you could run this application then add some dependencies in your `pom.xml`
		
					<!-- Logging -->
				<dependency>
					<groupId>org.slf4j</groupId>
					<artifactId>slf4j-api</artifactId>
					<version>${org.slf4j-version}</version>
				</dependency>
				<dependency>
					<groupId>org.slf4j</groupId>
					<artifactId>jcl-over-slf4j</artifactId>
					<version>${org.slf4j-version}</version>
				</dependency>
				<dependency>
					<groupId>org.slf4j</groupId>
					<artifactId>slf4j-log4j12</artifactId>
					<version>${org.slf4j-version}</version>
					<scope>runtime</scope>
				</dependency>
				
		> These dependencies are using slf4j at logging the details
		
				<!--  Gson -->
				<dependency>
					<groupId>com.google.code.gson</groupId>
					<artifactId>gson</artifactId>
					<version>2.2.2</version>
				</dependency>   
				
		> This dependency to load the `gson parser` for `json to object`
		
				<!--  Snake Yaml -->
				<dependency>
					<groupId>org.yaml</groupId>
					<artifactId>snakeyaml</artifactId>
					<version>1.12</version>
				</dependency>  
				
		> `snakeyaml` is parser for yaml file's
		
				<!-- Riak -->
				<dependency>
					<groupId>com.basho.riak</groupId>
					<artifactId>riak-client</artifactId>
					<version>1.1.0</version>
				</dependency>
				
		> `Riak` dependency is to create a api for riak java client
		
	###After the add the dependencies you need update the maven 
	
`Finally run the application using junit test classes or terminal via run` 
	
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

