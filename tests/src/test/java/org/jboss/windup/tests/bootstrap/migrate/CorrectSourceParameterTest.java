package org.jboss.windup.tests.bootstrap.migrate;

import org.jboss.windup.tests.bootstrap.AbstractBootstrapTestWithRules;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

public class CorrectSourceParameterTest extends AbstractBootstrapTestWithRules {
    @Rule
    public final TemporaryFolder tmp = new TemporaryFolder();

    @Test
    public void correctSourceParameter() throws IOException {
        bootstrap("--input", "../test-files/Windup1x-javaee-example-tiny.war",
                "--output", tmp.getRoot().getAbsolutePath(),
                "--source", "eap6",
                "--target", "eap7");

        assertTrue(capturedOutput().contains("WARNING: No packages were set in --packages."));
        assertTrue(capturedOutput().contains("Executing Windup"));
        assertTrue(capturedOutput().contains("Windup report created"));

        String indexHtml = new String(Files.readAllBytes(tmp.getRoot().toPath().resolve("index.html")), "UTF-8");
        assertTrue(indexHtml.contains("Windup1x-javaee-example-tiny.war"));
        assertTrue(indexHtml.contains("Decompiled Java File"));
        assertTrue(indexHtml.contains("Maven XML"));
    }
}
