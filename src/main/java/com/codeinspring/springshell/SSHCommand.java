package com.codeinspring.springshell;

import jakarta.validation.constraints.Size;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.logging.Logger;

import static java.lang.String.format;

@ShellComponent
public class SSHCommand
{
    Logger log = Logger.getLogger(SSHCommand.class.getName());

    /*
    * input == ssh -s my-machine
    * */
    @ShellMethod(value = "connect to remote server")
    public void ssh(@ShellOption(value = "-s") String remoteServer)
    {
        log.info(format("Logged to machine '%s'", remoteServer));
    }

    /*
     * input == my-ssh -s -remote-server test
     * */
    @ShellMethod(key = "my-ssh", value = "connect to remote server")
    public void myssh(@ShellOption(value = "-s") String remoteServer)
    {
        log.info(format("Logged to machine '%s'", remoteServer));
    }

    /*
     * input == my-ssh1 my-machine
     * */
    @ShellMethod(key = "my-ssh1", prefix = "-", value = "connect to remote server")
    public void my_ssh(@ShellOption String remoteServer)
    {
        log.info(format("Logged to machine '%s'", remoteServer));
    }

    @ShellMethod(value = "ssh agent") public void sshAgent(
            @ShellOption(value = "--a")
            @Size(min = 2, max = 10) String agent)
    {
        log.info(format("adding agent '%s'", agent));
    }
}