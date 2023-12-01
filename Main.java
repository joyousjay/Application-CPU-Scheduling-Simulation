public class Main {
    public static void main(String args[]) {
        GUI gui;
        
        Application outlook;
        Application youtube;
        Application chrome;

        String[] msOutlookProcesses = {"P1", "P2", "P3" };
        int[] msOutlookProcessesArrivalOrder = { 1, 2, 3 };
        int[] msOutlookProcessesBurstTimes = {2, 4, 6 };

        String[] youtubeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6" };
        int[] youtubeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6 };
        int[] youtubeProcessesBurstTimes = { 11, 9, 7, 5, 3, 1 };

        String[] chromeProcesses = { "P1", "P2", "P3", "P4", "P5", "P6", "P7" , "P8" , "P9" , "P10" };
        int[] chromeProcessesArrivalOrder = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] chromeProcessesBurstTimes = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };

        //sets the application details to the application
        outlook = new Application(msOutlookProcesses, msOutlookProcessesArrivalOrder, msOutlookProcessesBurstTimes);
        youtube = new Application(youtubeProcesses, youtubeProcessesArrivalOrder, youtubeProcessesBurstTimes);
        chrome = new Application(chromeProcesses, chromeProcessesArrivalOrder, chromeProcessesBurstTimes);

        outlook.setName("Microsoft Outlook");
        youtube.setName("YouTube");
        chrome.setName("Google Chrome");
        
        gui = new GUI(outlook, youtube, chrome);
        gui.create();

    }
}