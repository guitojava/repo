package gr.boomer.fm.checkandfix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class HelperCheckAndFix {

    static int numLines = 100;

    public static void printOutputForInvalid_MissingDirs() {
        HelperCheckAndFix.printNthListRows("invalid SysDirs                          Result", FMOutputParams.invalidSysDirsResults3, numLines);
        HelperCheckAndFix.printNthListRows("missing SysDirs                          Result", FMOutputParams.missingSysDirsResults4, numLines);
        HelperCheckAndFix.printNthListRows("invalid RootDirs                          Result", FMOutputParams.invalidRootDirsResults, numLines);
        HelperCheckAndFix.printNthListRows("missing RootDirs                          Result", FMOutputParams.missingRootDirsResults, numLines);
    }

    public static void printOutputForFiles() {
        HelperCheckAndFix.printNthListRows("PrimerConnect pcFileNotFoundOnDisk                Result", FMOutputParams.primerConnectFilesNotFoundOnDiskResults, numLines);
        HelperCheckAndFix.printNthListRows("PrimerConnect sysFileNotFoundInDB                 Result ", FMOutputParams.primerConnectSysFileNotFoundInDBResults, numLines);
        HelperCheckAndFix.printNthListRows("Root Files NotFoundOnDisk                         Result", FMOutputParams.rootFilesNotFoundOnDiskResults, numLines);
        HelperCheckAndFix.printNthListRows("Root sysFileNotFoundInDB RootDB                   Result ", FMOutputParams.rootSysFilesNotFoundInDBResults, numLines);
    }

    public static HashMap<String, String> getHashMapDIR(String fileName) {
        HashMap<String, String> map = new HashMap<>();
        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {

                if (!line.endsWith(FMInputParams.FILE)) {
                    // IT is a DIR
                    String[] parts = line.split(FMInputParams.PATH_SEPARATOR, 3);
                    if (parts.length >= 3) {
                        String key = parts[0];
                        String value = parts[1];
                        String level = parts[2];
                        // check here if last DIR
                        //    System.out.println ( key + "   " + value + "    "+ level );
                        if (value.equals(FMInputParams.DIR.replace(FMInputParams.PATH_SEPARATOR, ""))) {
                            map.put(key, value + FMInputParams.PATH_SEPARATOR + level);
                        }
                    } else {
                        System.out.println(FMInputParams.ERROR + "  ignoring line: " + line);
                        return null;
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public static HashMap<String, String> getHashMapForFILE(String fileName) {
        HashMap<String, String> map = new HashMap<>();
        String line;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null) {
                if (line.endsWith(FMInputParams.FILE)) {
                    String[] parts = line.split(FMInputParams.PATH_SEPARATOR, 2);
                    if (parts.length >= 2) {
                        String key = parts[0];
                        //    System.out.println ( key + "   " + value  );
                        String value = parts[1];
                        if (value.equals(FMInputParams.FILE.replace(FMInputParams.PATH_SEPARATOR, ""))) {
                            map.put(key, value);
                        }
                    } else {
                        System.out.println(FMInputParams.ERROR + "  ignoring line: " + line);
                        return null;
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public static List<String> loadTextFileLinesIntoStringList(String fileName) {
        List<String> validDirsList = new ArrayList<>();// from a file
        System.out.println("===========  LOAD VALID Sub DirectoryStructure from file ::     " + fileName);
        //  URL res = FMHelper.class.getClassLoader().getResource(fileName);
        try {
            InputStream inputStream = HelperCheckAndFix.class.getClassLoader().getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            for (String line; (line = reader.readLine()) != null; ) {
                // Process line
                validDirsList.add(line);
            }
            reader.close();
            streamReader.close();
            inputStream.close();
        } catch (Exception ex) {
            System.out.println("===========  ERROR -- Can open file::     " + fileName);
            validDirsList = null;
            ex.printStackTrace();
        }
        return validDirsList;
    }

    public static void removeAsWeMustApplyIgnoreList(ArrayList<String> tempInvalidDirs, List<String> validDirIgnoreList) {
        for (String ignoreDir : validDirIgnoreList) {
            ignoreDir = ignoreDir.replace("/", "");
            Iterator<String> i = tempInvalidDirs.iterator();
            while (i.hasNext()) {
                String r = i.next();
                if (r.contains(ignoreDir)) {
                    i.remove();
                }
            }
        }
    }

    public static Map<String, String> filterMapForCheckDirForRoots(Map<String, String> hashMap, String companyName) {
        if (hashMap == null || hashMap.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, String> result = hashMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = filterKeyContainsString(result, "/root/");
        result = filterKeyContainsString(result, companyName);
        result = filterValueContainsString(result, "DIR");
        return result;
    }

    public static Map<String, String> filterMapForCheckDirForYears(Map<String, String> hashMap, List<String> years, String companyName) {
        if (hashMap == null || hashMap.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, String> result = hashMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = filterKeyContainsString(result, companyName);
        result = filterMapYears(result, years);
        result = filterValueContainsString(result, "DIR");
        return result;
    }

    public static Map<String, String> filterMapForCheckFiles(Map<String, String> hashMap, List<String> years, String companyName) {
        if (hashMap == null || hashMap.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, String> result = hashMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result = filterValueContainsString(result, "FILE");
        result = filterKeyContainsString(result, companyName);
        result = filterMapYears(result, years);
        return result;
    }

    public static Map<String, String> filterMapYears(Map<String, String> hashMap, List<String> years) {
        System.out.println(" filterMap for years " + years);
        Map<String, String> temp = null;
        for (String year : years) {
            temp = hashMap.entrySet()
                    .stream()
                    .filter(map -> map.getKey().contains(year))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }
        return temp;
    }

    public static Map<String, String> filterKeyContainsString(Map<String, String> hashMap, String containsString ) {
        Map<String, String> temp = hashMap.entrySet()
                .stream()
                .filter(map -> map.getKey().contains(containsString))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return temp;
    }

    public static Map<String, String> filterKeyNOTContainsString(Map<String, String> hashMap, String notContainsString) {
        Map<String, String> temp = hashMap.entrySet()
                .stream()
                .filter(map -> !map.getKey().contains(notContainsString))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return temp;
    }

    public static Map<String, String> filterValueContainsString(Map<String, String> hashMap, String containsString ) {
        Map<String, String> temp = hashMap.entrySet()
                .stream()
                .filter(map -> map.getValue().contains(containsString))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return temp;
    }

    public static Map<String, String> filterValueNOTContainsString(Map<String, String> hashMap, String notContainsString) {
        Map<String, String> temp = hashMap.entrySet()
                .stream()
                .filter(map -> !map.getValue().contains(notContainsString))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return temp;
    }

    public static void printNthListRows(String header, List<String> stringList, int numToPrint) {
        System.out.println(header + " results size " + stringList.size());
        if (!stringList.isEmpty()) {
            if (numToPrint < stringList.size()) {
                for (int i = 0; i < numToPrint; i++) {
                    System.out.println("" + stringList.get(i));
                }
            } else {
                for (String s : stringList) {
                    System.out.println("" + s);
                }
            }
        }
    }

    public static void printMap(String header, Map<String, String> map) {
        System.out.println(header);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("" + entry.getKey() + "     " + entry.getValue());
        }
    }

    public static void printStringArrayList(String header, String msgPrefix, ArrayList<String> stringList) {
        System.out.println(header);
        for (int i = 0; i < stringList.size(); i++) {
            String ret = stringList.get(i);
            System.out.println(msgPrefix + ret);
        }
    }

    public static void printStringArrayList(String header, ArrayList<String> stringList) {
        System.out.println(header);
        for (int i = 0; i < stringList.size(); i++) {
            String ret = stringList.get(i);
            System.out.println(ret);
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}

