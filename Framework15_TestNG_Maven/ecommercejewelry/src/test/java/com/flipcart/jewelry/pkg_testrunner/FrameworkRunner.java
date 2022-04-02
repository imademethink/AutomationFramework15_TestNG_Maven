package com.flipcart.jewelry.pkg_testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

// RunWith annotation in Junit
@RunWith(Cucumber.class)

@CucumberOptions(
   features   = {"src/test/resources/features"},
   glue       = {"com.flipcart.jewelry.pkg_stepdefinition"},
   strict     = true,
   monochrome = false,      // little fancy coloured logs on console

   //tags       = {"@empty3"},               // single tag
   //tags       = {"@empty , @empty3"},      // OR operation
   //tags       = {"@empty","@empty2"},      // AND operation
   tags       = {"@simple"},
   //tags       = {"~@empty3"},             // single tag (NOT operation)

   // format = {},   // deprecated, instead use plugin

   // plugin section
   // {"pretty"}   -  Prints the Gherkin source with additional colors and stack traces for errors
   // {"html:folder1/folder2"} - This will generate a HTML report at the location mentioned
   // {"json:folder1/folder2/cucumber.json"} -
   //                This will generate a Json format report at the location mentioned for post processing
   // {"junit:folder1/folder2/cucumber.xml"}
   //                This will generate a Xml format report at the location mentioned for post processing

   plugin = {"pretty"}
   //plugin = {"html:target1"}
   //plugin = {"json:target2/cucumber.json"}
   //plugin = {"junit:target3/cucumber.xml"}
   //plugin = {"html:target1","junit:target3/cucumber.xml"}
)

public class FrameworkRunner extends AbstractTestNGCucumberTests
{


}


// 	Cucumber integration
//	     Running TestNG file, running using maven commands
//  1. Run without CucumberOptions
//      Cucumber needs at least one feature file to work on
//	     Feature file, step definition etc
//  2. Run with CucumberOptions
//		 Junit based @RunWith and AbstractTestNGCucumberTests
//       Cucumber options provides the necessary control desired by the user
//          Control of test filtering using tags option
//          linking the scenario statements to the actual java code (step definition)
//          other options e.g. strict, monochrome
//  3. Run with tags
//          Single tag
//          Multi tags (AND, OR, NOT operation)
//  4. Passing tags from command line or VM options
//          VM options   single tag -->                    "-Dcucumber.options=--tags @empty2 "
//          VM options   single tag NOT operation -->      "-Dcucumber.options=--tags ~@empty2 "
//          VM options   multiple tag   OR operation -->   "-Dcucumber.options=--tags @tag1,@tag2"
//          VM options   multiple tag  AND operation -->   "-Dcucumber.options=--tags '@empty3' --tags '@empty'"
//  5. Run with strict=true/ false,    monochrome=true/ false
//  6. Run with different types of plugin or all of them
//  7. Logging with log4j

