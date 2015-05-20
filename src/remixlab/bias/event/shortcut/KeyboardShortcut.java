/*********************************************************************************
 * bias_tree
 * Copyright (c) 2014 National University of Colombia, https://github.com/remixlab
 * @author Jean Pierre Charalambos, http://otrolado.info/
 *
 * All rights reserved. Library that eases the creation of interactive
 * scenes, released under the terms of the GNU Public License v3.0
 * which is available at http://www.gnu.org/licenses/gpl.html
 *********************************************************************************/

package remixlab.bias.event.shortcut;

import remixlab.bias.core.BogusEvent;
import remixlab.util.Copyable;

/**
 * This class represents keyboard shortcuts.
 * <p>
 * Keyboard shortcuts can be of one out of two forms: 1. Characters (e.g., 'a'); 2. Virtual keys (e.g., right arrow
 * key); or, 2. Key combinations (e.g., CTRL key + virtual key representing 'a').
 */
public final class KeyboardShortcut extends Shortcut implements Copyable {
	/**
	 * Defines a keyboard shortcut from the given character.
	 * 
	 * @param vk
	 *          the virtual key that defines the keyboard shthis.vKey = new Integer(other.vKey);ortcut.
	 */
	public KeyboardShortcut(Integer vk) {
		super(BogusEvent.NO_MODIFIER_MASK, vk);
	}

	/**
	 * Defines a keyboard shortcut from the given modifier mask and virtual key combination.
	 * 
	 * @param m
	 *          the mask
	 * @param vk
	 *          the virtual key that defines the keyboard shortcut.
	 */
	public KeyboardShortcut(Integer m, Integer vk) {
		super(m, vk);
	}

	protected KeyboardShortcut(KeyboardShortcut other) {
		super(other);
	}

	@Override
	public KeyboardShortcut get() {
		return new KeyboardShortcut(this);
	}
}
