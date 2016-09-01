# Live Stub

Live Stub is a Java agent library which allows to instrument bytecode.
It allows to mutate methods' implementation without source code 
modification. It can be useful for integration testing purposes or local 
debug sessions, when reproducing the whole application environment 
(databases, third party services) is a rather difficult task.

## Downloads

Live Stub latest release distributive is available [here]
(https://bintray.com/coolgadv/cdv-generic-repository/download_file?file_path=live-stub-distributive-1.1.zip).

It is also available as a Maven dependency.

Repository:
```xml
<repository>
    <id>cdv-repo</id>
    <url>http://dl.bintray.com/coolgadv/cdv-maven-repository</url>
</repository>
```
Dependency:
```xml
<dependency>
    <groupId>cdv.ls</groupId>
    <artifactId>live-stub-distributive</artifactId>
    <version>1.1</version>
    <type>zip</type>
</dependency>
```

## Setup

Unpack Live Stub distributive, create configuration file 
(read **Configuration format** section for details) and add the following
command line arguments to your application's start script:

* `-javaagent:<path to live stub jar file>` - 
standard way to specify Java agent library for your application. 
This parameter is mandatory.

* `-Dlive-stub.configuration-location=<path to configuration file>` - 
path to configuration file for Live Stub. This parameter is mandatory.

* `-Dlive-stub.verbose=<true or false>` - 
enables or disables diagnostic console output. This parameter is optional. Default value is `false`.

## Configuration format

XML format is described by the following [scheme](https://github.com/CoolgaDV/live-stub/blob/master/live-stub-agent/src/main/resources/xsd/configuration.xsd).
Here is an example of configuration file:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration xmlns="http://ls.cdv/configuration">
    <class name="foo.Bar">
        <method name="baz">
            <body>System.out.println("body");</body>
        </method>
    </class>
</configuration>
```

* `configuration` is a root tag, containing 1 or more classes. XML namespace is mandatory.

* `class` tag specifies a class for bytecode mutation. 
`name` attribute specifies a full class name and it is mandatory. 
`class` tag should contain one or more `method` tags.

* `method` specifies a method for bytecode mutation.
`name` attribute specifies a method name and it is mandatory.
`method` tag should contain at least on of the following tags: `before`, `body` and `after` 

* `before` - block of code that will be executed before the original method body.
* `body` - block of code that will be executed instead of the original method body. 
If this tag specified, `before` and `after` tags should not be used.
* `after` - block of code that will be executed after the original method body in finally block 
(in order to guarantee code execution).
