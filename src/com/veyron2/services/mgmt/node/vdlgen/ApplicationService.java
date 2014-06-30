// This file was auto-generated by the veyron vdl tool.
// Source: node.vdl
package com.veyron2.services.mgmt.node.vdlgen;

import com.veyron2.ipc.ServerContext;
import com.veyron2.ipc.VeyronException;
import java.util.ArrayList;

/**
 * Application can be used to manage applications on a device. The
 * idea is that this interace will be invoked using a veyron name that
 * identifies the application and its installations and instances
 * where applicable.
 *
 * In particular, the interface methods can be divided into three
 * groups based on their intended receiver:
 *
 * 1) Method receiver is an application:
 * -- Install()
 *
 * 2) Method receiver is an application installation:
 * -- Start()
 * -- Uninstall()
 * -- Update()
 *
 * 3) Method receiver is application installation instance:
 * -- Refresh()
 * -- Restart()
 * -- Resume()
 * -- Stop()
 * -- Suspend()
 *
 * For groups 2) and 3), the suffix that specifies the receiver can
 * optionally omit the installation and/or instance, in which case the
 * operation applies to all installations and/or instances in the
 * scope of the suffix.
 *
 * Examples:
 *
 * device/apps/maps.Start() starts an instance of all maps application
 * installations.
 *
 * device/apps/maps/installation.Start() starts an instance of the
 * maps application installation identified by the given suffix.
 *
 * device/apps/maps.Refresh() refreshes the state of all instances of
 * all maps application installations.
 *
 * device/apps/maps/installation.Refresh() refreshes the state of all
 * instances of the maps application installation identified by the
 * given suffix.
 *
 * device/apps/maps/installation/instance.Refresh() refreshes the
 * state of the maps application installation instance identified by
 * the given suffix.
 *
 * Further, the following methods complement one another:
 * -- Install() and Uninstall()
 * -- Start() and Stop()
 * -- Suspend() and Resume()
 *
 * Finally, an application installation instance can be in one of
 * three abstract states: 1) "does not exist", 2) "running", or 3)
 * "suspended". The interface methods transition between these
 * abstract states using the following state machine:
 *
 * apply(Start(), "does not exists") = "running"
 * apply(Refresh(), "running") = "running"
 * apply(Refresh(), "suspended") = "suspended"
 * apply(Restart(), "running") = "running"
 * apply(Restart(), "suspended") = "running"
 * apply(Resume(), "suspended") = "running"
 * apply(Resume(), "running") = "running"
 * apply(Stop(), "running") = "does not exist"
 * apply(Stop(), "suspended") = "does not exist"
 * apply(Suspend(), "running") = "suspended"
 * apply(Suspend(), "suspended") = "suspended"
 *
 * In other words, invoking any method using an existing application
 * installation instance as a receiver is well-defined.
**/
public interface ApplicationService { 
	// Install installs the latest version of the application and
// returns a veyron name that identifies the new
// installation. Optionally, veyron name suffix can be used to
// specify the application version to be installed. If no version is
// specified, the latest version is installed.
	public String install(ServerContext context) throws VeyronException;
	// Refresh refreshes the state of application installation(s)
// instance(s).
	public void refresh(ServerContext context) throws VeyronException;
	// Restart restarts execution of application installation(s)
// instance(s).
	public void restart(ServerContext context) throws VeyronException;
	// Resume resumes execution of application installation(s)
// instance(s).
	public void resume(ServerContext context) throws VeyronException;
	// Revert reverts application installation(s) to the most recent
// previous installation.
	public void revert(ServerContext context) throws VeyronException;
	// Start starts an instance of application installation(s) and
// returns the veyron name(s) that identifies/identify the new
// instance(s).
	public ArrayList<String> start(ServerContext context) throws VeyronException;
	// Stop attempts a clean shutdown of application installation(s)
// instance(s). If the deadline is non-zero and the instance(s) in
// questions are still running after the given deadline, shutdown of
// the instance(s) is enforced.
//
// TODO(jsimsa): Switch deadline to time.Duration when built-in types
// are implemented.
	public void stop(ServerContext context, long deadline) throws VeyronException;
	// Suspend suspends execution of application installation(s)
// instance(s).
	public void suspend(ServerContext context) throws VeyronException;
	// Uninstall uninstalls application installation(s).
	public void uninstall(ServerContext context) throws VeyronException;
	// Update updates application installation(s) version. Optionally,
// veyron name suffix can be used to specify the application version
// to which the installation(s) should be updated. If no version is
// specified, the installation(s) are updated to the latest version.
	public void update(ServerContext context) throws VeyronException;
}
