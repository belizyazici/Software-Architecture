class EntertainmentFacade {
    private static EntertainmentFacade instance;
    private TV tv;
    private SoundBar soundBar;
    private StreamingDevice streamingDevice;

    private EntertainmentFacade(TV tv, SoundBar soundBar, StreamingDevice streamingDevice) {
        this.tv = tv;
        this.soundBar = soundBar;
        this.streamingDevice = streamingDevice;
    }

    public static EntertainmentFacade getInstance(TV tv, SoundBar soundBar, StreamingDevice streamingDevice) {
        if (instance == null) {
            instance = new EntertainmentFacade(tv, soundBar, streamingDevice);
        }
        return instance;
    }

    public void watchMovie(String app) {
        tv.turnOn();
        soundBar.on();
        soundBar.changeMod("Cinema");
        streamingDevice.startDevice();
        streamingDevice.chooseApp(app);
        System.out.println("Enjoy your movie on " + app + "...");
    }

    public void watchTVShow(String app) {
        tv.turnOn();
        soundBar.on();
        soundBar.changeMod("TV Show");
        streamingDevice.startDevice();
        streamingDevice.chooseApp(app);
        System.out.println("Enjoy your TV show on " + app + "...");
    }
}

 class FacadeDemo {
    public static void main(String[] args) {
        TV led = new TV();
        SoundBar lg = new SoundBar();
        StreamingDevice viewsonic = new StreamingDevice();

        EntertainmentFacade facade = EntertainmentFacade.getInstance(led, lg, viewsonic);

        // User's request
        System.out.println("I want to watch a movie on Netflix...");
        facade.watchMovie("Netflix");

        // Another request
        System.out.println("\nI want to watch a TV show on Exxen...");
        facade.watchTVShow("Exxen");
    }
}

class TV {
    public void turnOn() {
        System.out.println("TV turned on...");
    }
}

class SoundBar {
    public void on() {
        System.out.println("The SoundBar is on now...");
    }

    public void changeMod(String mod) {
        if (mod.equals("Cinema") || mod.equals("TV Show"))
            System.out.println("The SoundBar mod changed to " + mod);
        else
            System.out.println("Wrong mod...");
    }
}

class StreamingDevice {
    public void startDevice() {
        System.out.println("Device is ready to stream...");
    }

    public void chooseApp(String app) {
        if (app.equals("Netflix") || app.equals("Exxen") || app.equals("Amazon Prime"))
            System.out.println("Streaming from " + app);
        else
            System.out.println("Wrong app name...");
    }
}
