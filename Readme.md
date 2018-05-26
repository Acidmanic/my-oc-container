About
===== 

MyOcContainer (My IOC Container) is supposed to be a very simple and lightweight  IOC container with mandatory features for a not very large project.  

How to use 
========== 

After importing the library into your project, the client code  will use an instance of the class __Resolver__.

you can register all your in use classes by calling one of the  overloads of the method _register(.)_.

Currently it does 'constructor injection's only. You can register different implementations for each class by providing  a tag parameter for _register_ method. a tag should be a string conforming to the convention of c-like languages variable names except it can have '-' and '.' in it.

you can also perform a small life time management for objects by providing a __LifetimeType__ parameter to the _register_ method. LifetimeType currently can be __Transient__ (default) or __Singleton__. all singleton objects will be stored inside the  __Resolver__ object. and resolver itself is not a singleton so you can have multiple scopes in your application.

to create an object, you should call on of the _resolve_ methods from __Resolver__ object. different _resolve_ methods will  resolve the given type with different strategies. for example, _resolveByTagOnly_ will resolve the class only if it's pre-registered with the given tag.  It's also possible to save resolver configurations to a file or load them from a file.

To load configurations from a pre-saved file, You can create the __Resolver__ object using its constructor which takes a string filePath parameter. the filePath would be the path to  the configuration file. configuration file is a white-space separated text file. each line will register one class. classes which are registered with  no tags, will have the default tag: "Default".

For saving the configurations, you can call the static method _save(.)_ from class __ConfigurationFile__. it takes a filePath argument and another argument of type  __DependancyDictionary__. a __DependancyDictionary__ object can be created and populated in code or you can get the object from an already configured resolver through its  _getRegisteredDependancies()_ method.

Example Code 
============  

consider we have the class __A__. which takes an argument of type __b__ and another argument of type __c__ in its constructor. where __b__ and __c__ are your abstractions. then you might have implementations __B1__ and __B2__ for __b__ and __C1__ and __C2__ for __c__.
a simple demo code would be like this:

```java

//Creating a resolver
Resolver resolver = new Resolver();

// Configuring your resolver
resolver.register(A.class, A.class);
resolver.register(A.class, A.class,"fancyOne",LifetimeType.Singleton);
resolver.register(b.class, B1.class);               // as default
resolver.register(b.class, B2.class,"fancyOne");    // fancy implementation
resolver.register(c.class, C1.class);               // default
resolver.register(c.class, C2.class,"fancyOne");    // fancy

// Using resolver
// this will make a transient A object with default implementation.
A aDefaultImplementation = resolver.resolve(A.class);
// this will make an A object with fancy implementation.
// this object will be a singleton.
A aFancyImplementation = resolver.resolve(A.class,"fancyOne"); 
```
<p>Good luck</p>
<p>Mani</p>   

__Contact:__

acidmanic.moayedi@gmail.com         









