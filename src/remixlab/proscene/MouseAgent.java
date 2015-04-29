/**************************************************************************************
 * ProScene (version 3.0.0)
 * Copyright (c) 2010-2014 National University of Colombia, https://github.com/remixlab
 * @author Jean Pierre Charalambos, http://otrolado.info/
 * 
 * All rights reserved. Library that eases the creation of interactive scenes
 * in Processing, released under the terms of the GNU Public License v3.0
 * which is available at http://www.gnu.org/licenses/gpl.html
 **************************************************************************************/

package remixlab.proscene;

import processing.core.PApplet;
import remixlab.dandelion.agent.*;
import remixlab.bias.core.BogusEvent;
import remixlab.bias.event.*;

/**
 * Proscene {@link remixlab.dandelion.agent.WheeledMouseAgent}.
 */
public class MouseAgent extends WheeledMouseAgent {
	public MouseAgent(Scene scn, String n) {
		super(scn, n);
		LEFT_ID = PApplet.LEFT;
		CENTER_ID = PApplet.CENTER;
		RIGHT_ID = PApplet.RIGHT;
		dragToArcball();
		// registration requires a call to PApplet.registerMethod("mouseEvent", motionAgent());
		// which is done in Scene.enableMotionAgent(), which also register the agent at the inputHandler
		inputHandler().unregisterAgent(this);
	}

	/**
	 * Hack to deal with this: https://github.com/processing/processing/issues/1693 is to override all the following so
	 * that:
	 * <p>
	 * <ol>
	 * <li>Whenever B_CENTER appears B_ALT should be present.</li>
	 * <li>Whenever B_RIGHT appears B_META should be present.</li>
	 * </ol>
	 */
	@Override
	public int buttonModifiersFix(int m, int button) {
		int mask = m;
		// ALT
		if (button == CENTER_ID)
			mask = (BogusEvent.ALT | m);
		// META
		else if (button == RIGHT_ID)
			mask = (BogusEvent.META | m);
		return mask;
	}

	/**
	 * Processing mouseEvent method to be registered at the PApplet's instance.
	 */
	public void mouseEvent(processing.event.MouseEvent e) {
		if (e.getAction() == processing.event.MouseEvent.MOVE) {
			move(new DOF2Event(currentEvent(), e.getX() - scene.originCorner().x(), e.getY() - scene.originCorner().y(),
					e.getModifiers(), MotionEvent.NO_ID));
		}
		if (e.getAction() == processing.event.MouseEvent.PRESS) {
			press(new DOF2Event(currentEvent(), e.getX() - scene.originCorner().x(),
					e.getY() - scene.originCorner().y(), e.getModifiers(), e.getButton()));
		}
		if (e.getAction() == processing.event.MouseEvent.DRAG) {
			drag(new DOF2Event(currentEvent(), e.getX() - scene.originCorner().x(), e.getY() - scene.originCorner().y(),
					e.getModifiers(), e.getButton()));
		}
		if (e.getAction() == processing.event.MouseEvent.RELEASE) {
			release(new DOF2Event(currentEvent(), e.getX() - scene.originCorner().x(), e.getY()
					- scene.originCorner().y(), e.getModifiers(), e.getButton()));
		}
		if (e.getAction() == processing.event.MouseEvent.WHEEL) {// e.getAction() = MouseEvent.WHEEL = 8
			wheel(new DOF1Event(e.getCount(), e.getModifiers(), WHEEL_ID));
		}
		if (e.getAction() == processing.event.MouseEvent.CLICK) {
			click(new ClickEvent(e.getX() - scene.originCorner().x(), e.getY() - scene.originCorner().y(),
					e.getModifiers(), e.getButton(), e.getCount()));
		}
	}
}