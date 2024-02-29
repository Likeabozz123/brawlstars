package xyz.gamars;

import de.jcm.discordgamesdk.Core;
import de.jcm.discordgamesdk.CreateParams;
import de.jcm.discordgamesdk.activity.Activity;

import java.io.File;
import java.time.Instant;

public class TempDiscordRP {

    public void start() {

        // Initialize the Core

        Core.init(new File("discord_game_sdk.dylib"));
        try(CreateParams params = new CreateParams())
        {
            params.setClientID(1212112030203519047L);
            params.setFlags(CreateParams.getDefaultFlags());

            try(Core core = new Core(params))
            {
                try(Activity activity = new Activity())
                {
                    activity.setDetails("Playing Brawlstars");

                    // Setting a start time causes an "elapsed" field to appear
                    activity.timestamps().setStart(Instant.now());

                    activity.assets().setLargeImage("main_logo");

                    // Finally, update the current activity to our activity
                    core.activityManager().updateActivity(activity);
                }

                // Run callbacks forever
                while(true)
                {
                    core.runCallbacks();
                    try
                    {
                        // Sleep a bit to save CPU
                        Thread.sleep(16);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
