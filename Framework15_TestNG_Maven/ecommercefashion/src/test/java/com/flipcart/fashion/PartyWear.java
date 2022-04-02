package com.flipcart.fashion;

import org.testng.annotations.Test;

public class PartyWear {

    @Test
    public void Test1(){
        System.out.println("Hello TestNG");
    }

}


//  Create simple maven project
//      mvn archetype:generate -DgroupId=com.flipcart.fashion
//      -DartifactId=ecommercefashion -DarchetypeArtifactId=maven-archetype-quickstart
//      -DinteractiveMode=false
//  Add TestNG dependency in pom.xml which will be managed by Maven automatically
//  Add @Test annotated method in a sample class
//  Get Testng.xml file template from https://testng.org/doc/documentation-main.html#testng-xml, https/ http correction
//  Add your sample class with proper package structure in xml file
//  Add header in xml file <?xml version="1.0" encoding="UTF-16"?>
//  Run the XML file (and not the java class), testng plugin by default installed in IntelliJ compare to Eclipse
//  Run using command line, batch file
//      dir /s /B *.java > sources.txt
//      set CLASSPATH=D:\TonyStark\Framework15_TestNG_Maven\ecommercefashion
//      javac @sources.txt -d bin
//      java -cp %CLASSPATH% org.testng.TestNG testng.xml
//  What if we can run TestNG file using maven commands and some changes in pom.xml file
//      Use surefire plugin in pom.xml
