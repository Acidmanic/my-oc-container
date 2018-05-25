About
=====

MyOcContainer (My IOC Container) is supposed to be a very simple and lightweight 
IOC container with mandatory features for a not very large project.


How to use
==========
After importing the project, the client code will use an instance 
of the class <b>Resolver</b>. 

you can register all your in-use classes by calling one of the 
overloads of the method <i>register(..)</i>. 

Currently it does constructor injections only.
You can register different implementations for each class by providing
a tag parameter for <i>register</i> method.
a tag should be a string conforming to the convention of c-like
languages variable names except it can have - in it.

you can also perform a small life time management for objects by providing a <b>LifetimeType</b> parameter to the <i>register</i> 
method. LifetimeType currently can be <b>Transient</b> (default) or
<b>Singleton</b>. all singleton objects will be stored inside the 
<b>Resolver</b> object. and resolver itself is not a singleton so 
you can have multipile scopes in your application.

to create an object, you should call on of the <i>resolve</i> methods
from <b>Resolver</b> object. different <i>resolve</i> methods will 
resolve the given type with different strategies. for example,
<i>resolveByTagOnly</i> will resolve the class only if it's
pre-registered with the given tag.

It's also possible to save resolver configurations to a file or
load them from the file.

To load configurations from a pre-saved file, You can create
the <b>Resolver</b> object using its constructor which takes a
filePath string parameter. the filePath would be the path to 
the configuration file.
configuration file is a white-space separated text file. each
line will register one class. classes which are registered with 
no tags, will have the default tag: "Default".

For saving the configurations, you can call the static method 
<i>save()</i> from class <b>ConfigurationFile</b>. it takes
a filePath argument and another argument of type 
<b>DependancyDictionary</b>. a <b>DependancyDictionary</b> object
can be created and populated in code or you can get the object
from an already configured resolver through its 
<i>getRegisteredDependancies()</i> method.

Example Code
============

consider we have the class <b>A</b>. which takes an argument
of type <b>b</b> and another argument of type <b>c</b> in its
constructor.
where <b>b</b> and <b>c</b> are your abstractions.
then you might have implementasions <b>B1</b> and <b>B2</b>
for <b>b</b> and <b>C1</b> and <b>C2</b> for <b>c</b>:

<code>

//Creating a resolver

Resolver resolver = new Resolver();

// Configuring your resolver

resolver.register(A.class, A.class);
resolver.register(A.class, A.class,"fancyOne",LifetimeType.Singleton);
resolver.register(b.class, B1.class); // as default
resolver.register(b.class, B2.class,"fancyOne");
resolver.register(c.class, C1.class);
resolver.register(c.class, C2.class,"fancyOne");




// Using resolver

// this will make a transient A object whith default implementations
A aDefaultImplementation = resolver.resolve(A.class);

// this will make an A object whith fancy implementation.
// this object will be a singleton.
A aFancyImplementation = resolver.resolve(A.class,"fancyOne");


</code>


<p>Good luck</p>
<p>Mani</p>


<br>
<br>

__Contact:__

acidmanic.moayedi@gmail.com








