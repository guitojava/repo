package gr.boomer.fm.checkandfix;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FMOutputParams {
    private static final PrintStream old = System.out;
    public static ByteArrayOutputStream output = new ByteArrayOutputStream();
    // files
    public static HashMap<String, String> filesDataSet = new HashMap<>();
    public static HashMap<String, String> pcFilesDataSet = new HashMap<>();
    public static HashMap<String, String> rootFilesDataSet= new HashMap<>();
    // valid info
    public static HashMap<String, List<String>> validDirsMapOfStringList = new HashMap<>();
    public static HashMap<String, List<String>> validDirsMapOfStringListForRootDir = new HashMap<>();
    public static List<String> validDirIgnoreList = new ArrayList<>();
    public static List<String> validDirIgnoreListRootDir = new ArrayList<>();
    // dirs
    public static Map<String, String> sysDirsResults;
    public static Map<String, String> sysDirsResultsForRoot;
    public static HashMap<String, String> dirsDataSet;
    public static Map<String, String> rootDirsDataSet;
    ////////////
    // output  data
    ///////////
    // files
    public static ArrayList<String> primerConnectFilesNotFoundOnDiskResults = new ArrayList<>();
    public static ArrayList<String> primerConnectSysFileNotFoundInDBResults = new ArrayList<>();
    public static ArrayList<String> rootFilesNotFoundOnDiskResults = new ArrayList<>();
    public static ArrayList<String> rootSysFilesNotFoundInDBResults = new ArrayList<>();
    //dirs
    public static ArrayList<String> invalidSysDirsResults3 = new ArrayList<>();
    public static ArrayList<String> missingSysDirsResults4 = new ArrayList<>();
    public static ArrayList<String> invalidRootDirsResults = new ArrayList<>();
    public static ArrayList<String> missingRootDirsResults = new ArrayList<>();
    // stats
    public static long totFiles = 0;
    public static long totDirs = 0;

    public static void setDefaultValues() {
        System.out.println("===========  set Defaults For OutputParams ");
        totFiles = 0;
        totDirs = 0;
        filesDataSet = new HashMap<>();
        pcFilesDataSet = new HashMap<>();
        rootFilesDataSet= new HashMap<>();
        validDirsMapOfStringList = new HashMap<>();
        validDirsMapOfStringListForRootDir = new HashMap<>();
        primerConnectFilesNotFoundOnDiskResults = new ArrayList<>();
        primerConnectSysFileNotFoundInDBResults = new ArrayList<>();
        rootFilesNotFoundOnDiskResults = new ArrayList<>();
        rootSysFilesNotFoundInDBResults = new ArrayList<>();
        invalidSysDirsResults3 = new ArrayList<>();
        missingSysDirsResults4 = new ArrayList<>();
        invalidRootDirsResults = new ArrayList<>();
        missingRootDirsResults = new ArrayList<>();
        output.reset();
    }
    public static void startRecordingOutput() {
        // start recording  output to print latter
        System.out.println("Start recording text-output ..O.O.");
        PrintStream ps = new PrintStream(output);
        System.setOut(ps);
    }
    public static void stopRecordingOutput() {
        // stop recording  output to print latter
        System.out.println("Stop recording  text-output ..X.X.");
        System.out.flush();
        System.setOut(old);
        System.out.println(output.toString());
    }
    public static String getOutput(){
        return output.toString();
    }
}
