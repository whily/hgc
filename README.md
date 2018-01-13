# hgc


hgc
===

hgc is scala library for historical geography of China.

Chinese calendar is a Scala library for Chinese Calendar.

Features to include:

* Historical maps of China between BC 772 to AD 1644.

For more information about hgc, please go to
  <https://github.com/whily/hgc>

Wiki pages can be found at
  <https://wiki.github.com/whily/hgc>

Development
-----------

The following tools are needed to build hgc from source:

* JDK version 6/78 from <http://www.java.com> if Java is not available.
  Note that JDK is preinstalled on Mac OS X and available via package manager
  on many Linux systems.
* Scala (2.11.11)
* sbt (0.13.16)

The project follows general sbt architecture, therefore normal sbt
commands can be used to build the library: compile, doc, test,
etc. For details, please refer
<http://scala.micronauticsresearch.com/sbt/useful-sbt-commands>.

Currently the library is not published to any public repository
yet. To use this library with your project, you need to download the
source code, and run `sbt publish-local` in your command line. Then,
include following line in your sbt configuration file.

          libraryDependencies += "net.whily" %% "hgc" % "0.0.1-SNAPSHOT"
