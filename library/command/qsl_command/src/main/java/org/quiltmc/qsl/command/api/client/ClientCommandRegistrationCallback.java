/*
 * Copyright 2021 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.qsl.command.api.client;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.quiltmc.qsl.base.api.event.ArrayEvent;
import org.quiltmc.qsl.command.impl.client.ClientCommandInternals;

/**
 * Event for registering client-side commands.
 *
 * @see ClientCommandManager
 */
@FunctionalInterface
@Environment(EnvType.CLIENT)
public interface ClientCommandRegistrationCallback {
	/**
	 * Gets the command registration event for commands with the {@code /} prefix.
	 *
	 * @return the event object
	 */
	static ArrayEvent<ClientCommandRegistrationCallback> event() {
		return event(ClientCommandManager.DEFAULT_PREFIX);
	}

	/**
	 * Gets the command registration event with a custom command prefix.
	 *
	 * @param prefix the command prefix
	 * @return the event object
	 * @throws IllegalArgumentException if the prefix {@linkplain Character#isLetterOrDigit(char) is a letter or a digit},
	 *                                  or if it {@linkplain Character#isWhitespace(char) is whitespace}.
	 */
	static ArrayEvent<ClientCommandRegistrationCallback> event(char prefix) {
		return ClientCommandInternals.event(prefix);
	}

	/**
	 * Called when client-side commands are registered.
	 *
	 * @param dispatcher the command dispatcher.
	 */
	void registerCommands(CommandDispatcher<QuiltClientCommandSource> dispatcher);
}
