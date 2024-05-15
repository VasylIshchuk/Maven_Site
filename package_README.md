To build a Java package using Maven, you should use:

* IN pom.xml: 
<distributionManagement>
    <repository>
      <id>github</id>
      <name>VasylIshchuk</name>
      <url>https://maven.pkg.github.com/VasylIshchuk/Maven_Site</url>
    </repository>
  </distributionManagement>
  
* IN ~/.m2/settings.xml:
  <settings xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <servers>
      <server>
        <id>github</id>
        <username>YOUR_GITHUB_USERNAME</username>
        <password>YOUR_GITHUB_TOKEN</password>
      </server>
    </servers>
  </settings>

  * IN terminal:
      mvn deploy
