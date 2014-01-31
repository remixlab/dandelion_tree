/*******************************************************************************
 * dandelion (version 1.0.0)
 * Copyright (c) 2014 National University of Colombia, https://github.com/remixlab
 * @author Jean Pierre Charalambos, http://otrolado.info/
 *     
 * All rights reserved. Library that eases the creation of interactive
 * scenes, released under the terms of the GNU Public License v3.0
 * which is available at http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package remixlab.dandelion.agent;

import remixlab.dandelion.core.*;
import remixlab.tersehandling.generic.agent.*;
import remixlab.tersehandling.generic.event.*;
import remixlab.tersehandling.generic.profile.*;

public class KeyboardAgent extends GenericKeyboardAgent<GenericKeyboardProfile<Constants.KeyboardAction>> implements Constants {	
	public KeyboardAgent(AbstractScene scn, String n) {
		super(new GenericKeyboardProfile<KeyboardAction>(), scn.terseHandler(), n);
		setDefaultGrabber(scn);

		// D e f a u l t s h o r t c u t s
		keyboardProfile().setShortcut('a', KeyboardAction.DRAW_AXIS);
		keyboardProfile().setShortcut('f', KeyboardAction.DRAW_FRAME_SELECTION_HINT);
		keyboardProfile().setShortcut('g', KeyboardAction.DRAW_GRID);
		keyboardProfile().setShortcut('m', KeyboardAction.ANIMATION);
		
		keyboardProfile().setShortcut('e', KeyboardAction.CAMERA_TYPE);
		keyboardProfile().setShortcut('h', KeyboardAction.GLOBAL_HELP);
		keyboardProfile().setShortcut('r', KeyboardAction.EDIT_EYE_PATH);

		keyboardProfile().setShortcut('s', KeyboardAction.INTERPOLATE_TO_FIT);
		keyboardProfile().setShortcut('S', KeyboardAction.SHOW_ALL);

		keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, TH_RIGHT, KeyboardAction.MOVE_EYE_RIGHT);
		keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, TH_LEFT, KeyboardAction.MOVE_EYE_LEFT);
		keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, TH_UP, KeyboardAction.MOVE_EYE_UP);
		keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, TH_DOWN, KeyboardAction.MOVE_EYE_DOWN);

		keyboardProfile().setShortcut((TH_ALT | GenericKeyboardEvent.TH_SHIFT), 'l',	KeyboardAction.MOVE_EYE_LEFT);
		
		//only one not working but horrible: 
		//keyboardProfile().setShortcut('1', KeyboardAction.PLAY_PATH);
		
		//keyboardProfile().setShortcut(49, KeyboardAction.PLAY_PATH);
		//keyboardProfile().setShortcut(TH_CTRL, 49, KeyboardAction.ADD_KEYFRAME_TO_PATH);
		//keyboardProfile().setShortcut(TH_ALT, 49, KeyboardAction.DELETE_PATH);
		//keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, '1', KeyboardAction.PLAY_PATH_1);
		
		keyboardProfile().setShortcut(TH_CTRL, '1', KeyboardAction.ADD_KEYFRAME_TO_PATH_1);
		keyboardProfile().setShortcut(TH_ALT, '1', KeyboardAction.DELETE_PATH_1);
		keyboardProfile().setShortcut(TH_CTRL, '2', KeyboardAction.ADD_KEYFRAME_TO_PATH_2);
		keyboardProfile().setShortcut(TH_ALT, '2', KeyboardAction.DELETE_PATH_2);
		keyboardProfile().setShortcut(TH_CTRL, '3', KeyboardAction.ADD_KEYFRAME_TO_PATH_3);
		keyboardProfile().setShortcut(TH_ALT, '3', KeyboardAction.DELETE_PATH_3);
		
		setKeyToPlayPath('1', 1);
		setKeyToPlayPath('2', 2);
		setKeyToPlayPath('3', 3);
		
	  //testing:
		//keyboardProfile().setShortcut('z', KeyboardAction.RESET_ARP);
	}
	
	public void setKeyToPlayPath(char key, int path) {			
		switch (path) {
		case 1 :
			keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, key, KeyboardAction.PLAY_PATH_1);
			break;
		case 2 :
			keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, key, KeyboardAction.PLAY_PATH_2);
			break;
		case 3 :
			keyboardProfile().setShortcut(TH_NOMODIFIER_MASK, key, KeyboardAction.PLAY_PATH_3);
			break;
		default :
			break;
		}		
	}
	
	/**
	public void setAddKeyFrameKeyboardModifier(int modifier) {
		if( modifier != EventConstants.TH_SHIFT &&
				modifier != EventConstants.TH_CTRL &&
				modifier != EventConstants.TH_ALT &&
				modifier != EventConstants.TH_ALT_GRAPH &&
				modifier != EventConstants.TH_META )
  			System.out.println("Expected a modifier here");
		else {
			keyboardProfile().setShortcut(TH_CTRL, '1', KeyboardAction.ADD_KEYFRAME_TO_PATH_1);
		}
	}
	
	public void setDeleteKeyFrameKeyboardModifier(int modifier) {
		if( modifier != EventConstants.TH_SHIFT &&
				modifier != EventConstants.TH_CTRL &&
				modifier != EventConstants.TH_ALT &&
				modifier != EventConstants.TH_ALT_GRAPH &&
				modifier != EventConstants.TH_META )
  			System.out.println("Expected a modifier here");
		else {
			keyboardProfile().setShortcut(TH_CTRL, '1', KeyboardAction.DELETE_PATH_1);
		}
	}
	*/
	
	@Override
	public GenericKeyboardEvent<KeyboardAction> feed() {
		return null;
	}

	@Override
	public GenericKeyboardProfile<KeyboardAction> keyboardProfile() {
		return profile;
	}
}
