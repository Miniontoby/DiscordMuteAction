package com.miniontoby.discordmuteaction;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

public class DiscordBot {
	private JDA client;

	public DiscordBot(String token) {
		JDABuilder builder = JDABuilder.createDefault(token);
		try {
			client = builder.build();
			client.awaitReady();
		} catch (Exception e) {
			System.out.println("Error: Could not login.");
			return;
		}
	}

	public void muteUser(String guild_id, String user_id, boolean mute) {
		Guild guild = client.getGuildById(guild_id);
		User user = client.getUserById(user_id);
		guild.mute(user, mute).queue();
	}

	public void shutdown() {
		client.shutdown();
	}
}
