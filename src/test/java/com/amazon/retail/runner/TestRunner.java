package com.amazon.retail.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources", glue = { "com.amazon.retail.steps" }, monochrome = false, plugin = {
		"json:target/cucumber.json" },stepNotifications = true, tags = "@tag1")
public class TestRunner {

}
