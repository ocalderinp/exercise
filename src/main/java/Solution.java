import com.google.gson.Gson;
import models.User;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static List<User> users = new ArrayList<>();
    private static Map<String, List<String>> auth_modules = new HashMap<>();
    private static Map<String, List<String>> content_modules = new HashMap<>();

    public static void main(String[] args) {
        Gson gson = new Gson();
        for (File file : getFiles()) {
            try (Reader reader = new FileReader(file.getPath())) {
                User user = gson.fromJson(reader, User.class);
                users.add(user);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (User u : users) {
            setupAutModulesMap(u);
            setupContentModulesMap(u);
        }

        String json = "{\n 'auth_module': \n" +
                        gson.toJson(auth_modules) + "\n" +
                        "'content_module': \n" +
                        gson.toJson(content_modules) +
                      "\n}";
        System.out.println("********* PARTE A ************* \n");
        System.out.println(json);
        System.out.println("******************************* \n");

        List<String> modules = new ArrayList<>();
        List<String> result = new ArrayList<>();
        int totalModules = auth_modules.keySet().size() + content_modules.keySet().size();
        for (User user : users){
            if (!modules.contains(user.getProvider().getAuth_module()) &&
                    !modules.contains(user.getProvider().getContent_module())) {
                modules.add(user.getProvider().getAuth_module());
                modules.add(user.getProvider().getContent_module());
                result.add(user.fileUse());
            }
            if(modules.size() == totalModules)
                break;
        }
        if(modules.size() != totalModules){
            for(User user : users) {
                if (!result.contains(user.fileUse())) {
                    if (!modules.contains(user.getProvider().getAuth_module())) {
                        modules.add(user.getProvider().getAuth_module());
                        result.add(user.fileUse());
                    }
                    if (!modules.contains(user.getProvider().getContent_module())) {
                        modules.add(user.getProvider().getContent_module());
                        result.add(user.fileUse());
                    }
                }
                if(modules.size() == totalModules)
                    break;
            }
        }

        System.out.println("********* PARTE B ************* \n");
        System.out.println(gson.toJson(result));
        System.out.println("******************************* \n");

    }

    public static File[] getFiles() {
        URL url = Solution.class.getClassLoader().getResource("data");
        File dataFolder = null;
        try {
            dataFolder = new File(url.toURI());
        } catch (URISyntaxException e) {
            dataFolder = new File(url.getPath());
        }
        File[] files = dataFolder.listFiles();
        return files;
    }


    public static void setupAutModulesMap(final User u) {
        if (auth_modules.containsKey(u.getProvider().getAuth_module())) {
            auth_modules.get(u.getProvider().getAuth_module()).add(u.fileUse());
        } else {
            List<String> auxList = new ArrayList<>();
            auxList.add(u.fileUse());
            auth_modules.put(u.getProvider().getAuth_module(), auxList);
        }
    }

    public static void setupContentModulesMap(final User u) {
        if (content_modules.containsKey(u.getProvider().getContent_module())) {
            content_modules.get(u.getProvider().getContent_module()).add(u.fileUse());
        } else {
            List<String> auxList = new ArrayList<>();
            auxList.add(u.fileUse());
            content_modules.put(u.getProvider().getContent_module(), auxList);
        }
    }




}
