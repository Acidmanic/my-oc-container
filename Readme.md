[![Build Status](https://travis-ci.org/Acidmanic/my-oc-container.svg?branch=develop)](https://travis-ci.org/Acidmanic/my-oc-container)
![License](https://img.shields.io/badge/license-GPL--3.0-blue.svg)

---------

<img src="https://maven.apache.org/images/maven-logo-black-on-white.png" width="90px" height="26px" />  <img src="https://raw.githubusercontent.com/gradle/gradle/master/gradle.png" width="100px" height="32px" />

-----


About
===== 

MyOcContainer (My IOC Container) is supposed to be a very simple and lightweight  IOC container with mandatory features for a small, to medium scale project.  



Download
======

MyOc Container is available on maven central. you can add a maven dependency in your pom.xml file using this snippet:

```xml
	<dependency>
		<groupId>com.acidmanic</groupId>
		<artifactId>myoccontainer</artifactId>
		<version>1.0.1</version>
	</dependency>
```

and to be used in gradle:

```
	compile 'com.acidmanic:myoccontainer:1.0.1'
```

**Jar package:** You can also get  [Latest Release](https://github.com/Acidmanic/my-oc-container/releases/latest)   from git-hub releases page. Simply download the **myoc-container.jar** from the release page and add it to your project classpath.


How To Use 
========== 
**Register then Resolve**!

First you will create and instance of the clas **Resolver**, which is the main IOC object. The **Resolver** class is *NOT* a singleton therefore you can have different resolvation profiles in your project. but in most cases, a singleton is the best fit.
With your **Resolver** object created, first you will configure it to know how to resolve each class by calling over-rides of register() method. After that, your resolver object can be used in project to produce your objects.


How To Use - Register (Configure):
-------------

you can register all of your in-use classes by calling one of the  overloads of the method _register(.)_.

You can register different implementations for each class by providing  a tag parameter for _register_ method. a tag should be a string conforming to the convention of c-like languages variable names except it can have '-' and '.' in it.

It also supports a fluent-like syntax. to register classes with you preference via a fluent syntax, you can call the _register()_ method without any arguments. then you can set any of the properties using provided functions:

|        Method   |                                                                                                    |
|:------------------|:-------------------------------------------------------------------------------|
|	bind(.)      |	sets the class you want to choose an implementation for |
|    to(.)   		 |  sets the class that would be the implementation |
|   bindToSelf(.) | configures the resolver to resolve as class to itself. In other words, ``` resolver.register().bindToSelf(A.class)``` is equivalent to ``` resolver.register().bind(A.class).to(A.class)``` |
|  taggedAs(.)   |  will set a tag for this registration |
|  livesAsA(.)     |  will take a LifetimeType argument to set the type of lifetime you want to use. |
|   withBuilder(.)  |   will register a builder to create the object.|

You can perform a small life time management for objects by providing a __LifetimeType__ parameter to the _register_ method. LifetimeType currently can be __Transient__ (default) or __Singleton__. all singleton objects will be stored inside the  __Resolver__ object. and resolver itself is not a singleton so you can have multiple scopes in your application.

You can call withBuilder(.) method, passing it an implementation of __Builder__ interface. you can also call this method by an inline implementation (anonymous class) of the __Builder__ interface. the main difference is that with the dependency created using first approuch can be saved in a config file, but by using an anonymous class (or a lambda expression), it would not be possible, and the dependency will be ommited when you save configurations.

You can create an installer calss by implementing the __Installer__ interface. such a class will contain a _configure(Registerer myoc)_ method where you can put all your configuration lines. the __Registerer__ argument of the _configure(.)_ method provides the fluent syntax registration methods.


How To Use - Resolve:
--------------------------


to create an object, you should call on of the _resolve_ methods from __Resolver__ object. different _resolve_ methods will  resolve the given type with different strategies. for example, _resolveByTagOnly_ will resolve the class only if it's pre-registered with the given tag.  It's also possible to save resolver configurations to a file or load them from a file.

To load configurations from a pre-saved file, You can create the __Resolver__ object using its constructor which takes a string filePath parameter. the filePath would be the path to  the configuration file. configuration file is a white-space separated text file. each line will register one class. classes which are registered with  no tags, will have the default tag: "Default".

For saving the configurations, you can call the static method _save(.)_ from class __ConfigurationFile__. it takes a filePath argument and another argument of type  __DependancyDictionary__. a __DependancyDictionary__ object can be created and populated in code or you can get the object from an already configured resolver through its  _getRegisteredDependancies()_ method.

There is a _resolveAll(.)_ method in __Resolver__ class wich will return an array of objects. the array contains one resolved object for each dependency registered for a given class.


Example Code 
============  

consider we have the class __A__. which takes an argument of type __b__ and another argument of type __c__ in its constructor. where __b__ and __c__ are your abstractions. then you might have implementations __B1__ and __B2__ for __b__ and __C1__ and __C2__ for __c__.
a simple demo code would be like this:

```java

//Creating a resolver
Resolver resolver = new Resolver();

// Configuring your resolver - normal argument call

resolver.register(A.class, A.class);
resolver.register(A.class, A.class,"fancyOne",LifetimeType.Singleton);
resolver.register(b.class, B1.class);               // as default
resolver.register(b.class, B2.class,"fancyOne");    // fancy implementation
resolver.register(c.class, C1.class);               // default
resolver.register(c.class, C2.class,"fancyOne");    // fancy

// Configuring your resolver - fluent like syntax

resolver.register().bindToSelf(A.class);  
resolver.register().bindToSelf(A.class).taggedAs("fancyOne").livesAsA(LifetimeType.Singleton); 
resolver.register().bind(b.class).to(B1.class);  
resolver.register().bind(b.class).to(B2.class).taggedAs("fancyOne");  
resolver.register().bind(c.class).to(C1.class);  
resolver.register().bind(b.class).to(C2.class).taggedAs("fancyOne");  

// Using resolver


// this will make a transient A object with default implementation.

A aDefaultImplementation = resolver.resolve(A.class);

// this will make an A object with fancy implementation.
// this object will be a singleton.

A aFancyImplementation = resolver.resolve(A.class,"fancyOne"); 


```

**Note:**
When you use the resolver to resolve a class to an object, you can also use *resolver.tryResolve(.)* methods instead of *resolver.resolve(.)* methods. The difference is that tryResolve(.) methods will not throw an exception, and instead they will return a null when they caught a problem resolving the given class.

__More Examples__

If you downloaded the code, you can take a look at test codes in the project in the package _test.myoccontainer.*_.


<p>Good luck</p>
<p>Mani</p>   

__Contact:__

acidmanic.moayedi@gmail.com         