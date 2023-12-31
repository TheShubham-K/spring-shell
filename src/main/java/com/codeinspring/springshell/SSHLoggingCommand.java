package com.codeinspring.springshell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.util.logging.Logger;

import static java.lang.String.format;

@ShellComponent
public class SSHLoggingCommand
{
    Logger log = Logger.getLogger(SSHLoggingCommand.class.getName());
    private boolean signedIn;

    @ShellMethod(value = "sign in")
    public void signIn()
    {
        this.signedIn = true;
        log.info("Signed In!");
    }

    @ShellMethod(value = "sign out")
    @ShellMethodAvailability("signOutAvailability")
    public void signOut()
    {
        this.signedIn = false;
        log.info("Signed out!");
    }
    @ShellMethod(value = "Change password")
    public void changePass(@ShellOption String newPass)
    {
        log.info(format("Changed password to '%s'", newPass));
    }

    @ShellMethodAvailability({"sign-out", "change-pass"})
    public Availability signOutAvailability()
    {
        return signedIn ?
                Availability.available() : Availability.unavailable("Must be signed in first");
    }

    @ShellMethod(value = "Add two integers together.", group = "Mathematical Commands")
    public int add(int a, int b) {
        return a + b;
    }
}