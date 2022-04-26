module com.miniontoby.discordmuteaction
{
    requires com.stream_pi.action_api;
    requires org.kordamp.ikonli.javafx;
    requires net.dv8tion.jda;

    requires java.desktop;

    provides com.stream_pi.action_api.externalplugin.ExternalPlugin with com.miniontoby.discordmuteaction.DiscordMuteAction;
}
