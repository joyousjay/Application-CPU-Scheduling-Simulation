public class Main {
    public static void main(String args[]) {
        GUI gui;
        Application[] applications;
        Application outlook;
        Application youtube;
        Application chrome;

        ProcessSet[] processSets;
        ProcessSet setOne;
        ProcessSet setTwo;
        ProcessSet setThree;
        
        //three sets of data with different process ids and arrival orders
        String[] setOneProcessIds = {"P1", "P2", "P3"};
        int[] setOneArrivalOrder = { 1, 2, 3 };

        String[] setTwoProcessIds = { "P1", "P2", "P3", "P4", "P5", "P6"};
        int[] setTwoArrivalOrder = { 1, 2, 3, 4, 5, 6 };

        String[] setThreeProcessIds = { "P1", "P2", "P3", "P4", "P5", "P6", "P7" , "P8" , "P9" , "P10" };
        int[] setThreeArrivalOrder = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        //burst times for applications
        int[] msOutlookProcessesBurstTimes = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
        int[] youtubeProcessesBurstTimes = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
        int[] chromeProcessesBurstTimes = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30 };
        

        //sets the application details to the application
        outlook = new Application(msOutlookProcessesBurstTimes);
        youtube = new Application(youtubeProcessesBurstTimes);
        chrome = new Application(chromeProcessesBurstTimes);

        applications = new Application[]{outlook, youtube, chrome};

        //sets the details of a process in each set 
        setOne = new ProcessSet(3, setOneProcessIds, setOneArrivalOrder);
        setTwo = new ProcessSet(6, setTwoProcessIds, setTwoArrivalOrder);
        setThree = new ProcessSet(10, setThreeProcessIds, setThreeArrivalOrder);
       
        processSets = new ProcessSet[]{setOne, setTwo, setThree};

        //set the name of the application
        outlook.setName("Microsoft Outlook");
        youtube.setName("YouTube");
        chrome.setName("Google Chrome");
        
        gui = new GUI(processSets, applications);
        gui.create();

    }
}