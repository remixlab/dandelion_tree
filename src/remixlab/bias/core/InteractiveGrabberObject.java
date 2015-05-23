/*********************************************************************************
 * bias_tree
 * Copyright (c) 2014 National University of Colombia, https://github.com/remixlab
 * @author Jean Pierre Charalambos, http://otrolado.info/
 *
 * All rights reserved. Library that eases the creation of interactive
 * scenes, released under the terms of the GNU Public License v3.0
 * which is available at http://www.gnu.org/licenses/gpl.html
 *********************************************************************************/

package remixlab.bias.core;

import remixlab.bias.branch.*;
import remixlab.bias.event.*;

public abstract class InteractiveGrabberObject<E extends Enum<E>> implements InteractiveGrabber<E>
/*
 * , Grabber //
 */
{
	Action<E>	action;

	/**
	 * Empty constructor.
	 */
	public InteractiveGrabberObject() {
	}

	/**
	 * Constructs and adds this grabber to the agent pool.
	 * 
	 * @see remixlab.bias.core.Agent#grabbers()
	 */
	public InteractiveGrabberObject(Agent agent, Branch<E, ? extends Action<E>, ?> actionAgent) {
		agent.addGrabber(this, actionAgent);
	}

	public E referenceAction() {
		return action != null ? action.referenceAction() : null;
	}

	@Override
	public Action<E> action() {
		return action;
	}

	@Override
	public void setAction(Action<E> a) {
		action = a;
	}

	/**
	 * Check if this object is the {@link remixlab.bias.core.Agent#inputGrabber()}. Returns {@code true} if this object
	 * grabs the agent and {@code false} otherwise.
	 */
	public boolean grabsInput(Agent agent) {
		return agent.inputGrabber() == this;
	}

	@Override
	public void performInteraction(BogusEvent event) {
		if (event instanceof KeyboardEvent)
			performInteraction((KeyboardEvent) event);
		if (event instanceof ClickEvent)
			performInteraction((ClickEvent) event);
		if (event instanceof DOF1Event)
			performInteraction((DOF1Event) event);
		if (event instanceof DOF2Event)
			performInteraction((DOF2Event) event);
		if (event instanceof DOF3Event)
			performInteraction((DOF3Event) event);
		if (event instanceof DOF6Event)
			performInteraction((DOF6Event) event);
	}

	// TODO : deal with warnings
	protected void performInteraction(KeyboardEvent event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(KeyboardEvent event)",
		// this.getClass().getName());
	}

	protected void performInteraction(ClickEvent event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(ClickEvent event)",
		// this.getClass().getName());
	}

	protected void performInteraction(DOF1Event event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(DOF1Event event)", this.getClass().getName());
	}

	protected void performInteraction(DOF2Event event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(DOF2Event event)", this.getClass().getName());
	}

	protected void performInteraction(DOF3Event event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(DOF3Event event)", this.getClass().getName());
	}

	protected void performInteraction(DOF6Event event) {
		// AbstractScene.showMissingImplementationWarning("performInteraction(DOF6Event event)", this.getClass().getName());
	}

	@Override
	public boolean checkIfGrabsInput(BogusEvent event) {
		if (event instanceof KeyboardEvent)
			return checkIfGrabsInput((KeyboardEvent) event);
		if (event instanceof ClickEvent)
			return checkIfGrabsInput((ClickEvent) event);
		if (event instanceof DOF1Event)
			return checkIfGrabsInput((DOF1Event) event);
		if (event instanceof DOF2Event)
			return checkIfGrabsInput((DOF2Event) event);
		if (event instanceof DOF3Event)
			return checkIfGrabsInput((DOF3Event) event);
		if (event instanceof DOF6Event)
			return checkIfGrabsInput((DOF6Event) event);
		return false;
	}

	protected boolean checkIfGrabsInput(KeyboardEvent event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(KeyboardEvent event)",
		// this.getClass().getName());
		return false;
	}

	protected boolean checkIfGrabsInput(ClickEvent event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(ClickEvent event)", this.getClass().getName());
		return false;
	}

	protected boolean checkIfGrabsInput(DOF1Event event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(DOF1Event event)", this.getClass().getName());
		return false;
	}

	protected boolean checkIfGrabsInput(DOF2Event event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(DOF2Event event)", this.getClass().getName());
		return false;
	}

	protected boolean checkIfGrabsInput(DOF3Event event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(DOF3Event event)", this.getClass().getName());
		return false;
	}

	protected boolean checkIfGrabsInput(DOF6Event event) {
		// AbstractScene.showMissingImplementationWarning("checkIfGrabsInput(DOF6Event event)", this.getClass().getName());
		return false;
	}
}
