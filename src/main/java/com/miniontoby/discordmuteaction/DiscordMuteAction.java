package com.miniontoby.discordmuteaction;

import com.stream_pi.action_api.actionproperty.property.*;
import com.stream_pi.action_api.externalplugin.ToggleAction;
import com.stream_pi.util.exception.MinorException;
import com.stream_pi.util.alert.StreamPiAlert;
import com.stream_pi.util.alert.StreamPiAlertType;
import com.stream_pi.util.version.Version;

import java.io.IOException;

public class DiscordMuteAction extends ToggleAction {
	public DiscordMuteAction() {
		setName("Discord Mute Action");
		setCategory("Discord");
		setAuthor("Miniontoby");
		setServerButtonGraphic("fab-discord");
		setHelpLink("https://edugit.org/Miniontoby/DiscordMuteAction");
		setVersion(new Version(1,0,0));
	}

	@Override
	public void initProperties() throws MinorException {
		Property botTokenProperty = new StringProperty("bot_token");
		botTokenProperty.setDisplayName("Bot Token");

		Property guildProperty = new StringProperty("guild_id");
		guildProperty.setDisplayName("Guild ID");

		Property userProperty = new StringProperty("user_id");
		userProperty.setDisplayName("User ID");

		addClientProperties(botTokenProperty, guildProperty, userProperty);
	}

	@Override
	public void onToggleOn() throws MinorException {
		muteUser(true);
	}

	@Override
	public void onToggleOff() throws MinorException {
		muteUser(false);
	}

	public void muteUser(boolean mute) throws MinorException {
		String botToken = getClientProperties().getSingleProperty("bot_token").getStringValue();
		String guild = getClientProperties().getSingleProperty("guild_id").getStringValue();
		String user = getClientProperties().getSingleProperty("user_id").getStringValue();

		if (botToken.isBlank()) {
			throwMinorException("No Bot Token specified!");
		}

		DiscordBot bot = new DiscordBot(botToken);
		bot.muteUser(guild, user, mute);
		bot.shutdown();
	}
}
