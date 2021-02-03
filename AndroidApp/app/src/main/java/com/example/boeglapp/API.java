package com.example.boeglapp;

import android.os.AsyncTask;
import android.os.Build;
import android.util.JsonReader;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class API {

    public String endpoint = "http://10.0.2.2:80/";

    public class AsyncGetTools extends AsyncTask<Void, Void, ArrayList<Tool>> {


        private ToolFragment frag;

        public AsyncGetTools(ToolFragment frag){
            this.frag = frag;
        }

        @Override
        protected ArrayList<Tool> doInBackground(Void... voids) {
            ArrayList<Tool> tools = new ArrayList<Tool>();
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=getAllTools&apiFunctionParams={}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());

                InputStream responseBody = con.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while(jsonReader.hasNext()){
                    if(jsonReader.nextName().equals("dataArray")) {
                        System.out.println("HI");
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            Tool t = new Tool();
                            while (jsonReader.hasNext()) {
                                if (jsonReader.nextName().equals("ID")) {
                                    t.Id = jsonReader.nextInt();
                                }
                                if (jsonReader.nextName().equals("NAME")) {
                                    t.Name = jsonReader.nextString();
                                }

                            }
                            tools.add(t);
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tools;
        }

        @Override
        protected void onPostExecute(ArrayList<Tool> tools) {
            if(frag != null){
                frag.updateTools(tools);
            }
        }
    }

    public class AsyncInsertTool extends AsyncTask<Void, Void, Void> {

        private String name;

        public AsyncInsertTool(String name){
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=insertTool&apiFunctionParams={\"name\":\"" + name + "\"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncUpdateTool extends AsyncTask<Void, Void, Void> {

        private int id;
        private String name;

        public AsyncUpdateTool(int id, String name){
            this.name = name;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=updateTool&apiFunctionParams={\"name\":\"" + name + "\",\"id\":" + id +"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncDeleteTool extends AsyncTask<Void, Void, Void> {

        private int id;

        public AsyncDeleteTool(int id){
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=deleteTool&apiFunctionParams={\"id\":" + id +"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncGetSites extends AsyncTask<Void, Void, ArrayList<Site>> {

        private SiteFragment frag;

        public AsyncGetSites(SiteFragment frag){
            this.frag = frag;
        }

        @Override
        protected ArrayList<Site> doInBackground(Void... voids) {
            ArrayList<Site> sites = new ArrayList<Site>();
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=getAllSites&apiFunctionParams={}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
                InputStream responseBody = con.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while(jsonReader.hasNext()){
                    if(jsonReader.nextName().equals("dataArray")) {
                        System.out.println("Sites");
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            Site t = new Site();
                            while (jsonReader.hasNext()) {
                                if (jsonReader.nextName().equals("ID")) {
                                    t.Id = jsonReader.nextInt();
                                }
                                if (jsonReader.nextName().equals("NAME")) {
                                    t.Name = jsonReader.nextString();
                                }

                            }
                            sites.add(t);
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sites;
        }

        @Override
        protected void onPostExecute(ArrayList<Site> sites) {
            if(frag != null){
                frag.updateSites(sites);
            }
        }
    }

    public class AsyncInsertSite extends AsyncTask<Void, Void, Void> {

        private String name;

        public AsyncInsertSite(String name){
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=insertSite&apiFunctionParams={\"name\":\"" + name + "\"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncUpdateSite extends AsyncTask<Void, Void, Void> {

        private int id;
        private String name;

        public AsyncUpdateSite(int id, String name){
            this.name = name;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=updateSite&apiFunctionParams={\"name\":\"" + name + "\",\"id\":" + id +"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncDeleteSite extends AsyncTask<Void, Void, Void> {

        private int id;

        public AsyncDeleteSite(int id){
            this.id = id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=deleteSite&apiFunctionParams={\"id\":" + id +"}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncGetHistoryByToolId extends AsyncTask<Void, Void, Void> {

        private ArrayList<History> histories = new ArrayList<History>();
        private HistoryFragment frag;
        private int toolid;

        public AsyncGetHistoryByToolId(HistoryFragment frag, int toolid){
            this.frag = frag;
            this.toolid = toolid;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                System.out.println(toolid);
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=getHistoriesByToolId&apiFunctionParams={\"toolid\":" + toolid + "}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
                InputStream responseBody = con.getInputStream();

                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while(jsonReader.hasNext()) {
                    if (jsonReader.nextName().equals("dataArray")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            History h = new History();
                            while (jsonReader.hasNext()) {
                                String name = jsonReader.nextName();

                                switch (name) {
                                    case "ID":
                                        h.Id = jsonReader.nextInt();
                                        break;
                                    case "EMPLOYEE":
                                        h.Employee = jsonReader.nextString();
                                        break;
                                    case "TO_DATE":
                                        h.To_date = jsonReader.nextInt();
                                        break;
                                    case "FROM_DATE":
                                        h.From_date = jsonReader.nextInt();
                                        break;
                                    case "TOOL_ID":
                                        h.Toolid = jsonReader.nextInt();
                                        break;
                                    case "SITE_ID":
                                        h.Siteid = jsonReader.nextInt();
                                        break;
                                    default:
                                        jsonReader.skipValue();
                                        break;
                                }
                            }
                            histories.add(h);
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            frag.updateHistories(histories);
        }
    }

    public class AsyncGetHistoryBySiteId extends AsyncTask<Void, Void, Void> {

        private ArrayList<History> histories = new ArrayList<History>();
        private HistoryFragment frag;
        private int siteid;

        public AsyncGetHistoryBySiteId(HistoryFragment frag, int siteid){
            this.frag = frag;
            this.siteid = siteid;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                System.out.println(siteid);
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=getHistoriesBySiteId&apiFunctionParams={\"siteid\":" + siteid + "}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());
                InputStream responseBody = con.getInputStream();

                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF8");
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while(jsonReader.hasNext()) {
                    if (jsonReader.nextName().equals("dataArray")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            History h = new History();
                            while (jsonReader.hasNext()) {
                                String name = jsonReader.nextName();

                                switch (name) {
                                    case "ID":
                                        h.Id = jsonReader.nextInt();
                                        break;
                                    case "EMPLOYEE":
                                        h.Employee = jsonReader.nextString();
                                        break;
                                    case "TO_DATE":
                                        h.To_date = jsonReader.nextInt();
                                        break;
                                    case "FROM_DATE":
                                        h.From_date = jsonReader.nextInt();
                                        break;
                                    case "TOOL_ID":
                                        h.Toolid = jsonReader.nextInt();
                                        break;
                                    case "SITE_ID":
                                        h.Siteid = jsonReader.nextInt();
                                        break;
                                    default:
                                        jsonReader.skipValue();
                                        break;
                                }
                            }
                            histories.add(h);
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    } else {
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
                jsonReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            frag.updateHistories(histories);
        }
    }

    public class AsyncInsertHistory extends AsyncTask<Void, Void, Void> {

        private String employee;
        private long from_date;
        private long to_date;
        private int toolid;
        private int siteid;

        public AsyncInsertHistory(String employee, long from_date, long to_date, int toolid, int siteid){
            this.employee = employee;
            this.from_date = from_date;
            this.to_date = to_date;
            this.toolid = toolid;
            this.siteid = siteid;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(
                        endpoint+"api?apiFunctionName=insertHistory&apiFunctionParams={" +
                        "\"employee\":\"" + employee + "\"," +
                        "\"from_date\":" + from_date + "," +
                        "\"to_date\":" + to_date + "," +
                        "\"toolid\":" + toolid + "," +
                        "\"siteid\":" + siteid + "}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncUpdateHistory extends AsyncTask<Void, Void, Void> {

        private int id;
        private String employee;
        private long from_date;
        private long to_date;
        private int toolid;
        private int siteid;

        public AsyncUpdateHistory(int id, String employee, long from_date, long to_date, int toolid, int siteid){
            this.id = id;
            this.employee = employee;
            this.from_date = from_date;
            this.to_date = to_date;
            this.toolid = toolid;
            this.siteid = siteid;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(
                        endpoint+"api?apiFunctionName=updateHistory&apiFunctionParams={" +
                                "\"id\":" + id + "," +
                                "\"employee\":\"" + employee + "\"," +
                                "\"from_date\":" + from_date + "," +
                                "\"to_date\":" + to_date + "," +
                                "\"toolid\":" + toolid + "," +
                                "\"siteid\":" + siteid + "}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class AsyncDeleteHistory extends AsyncTask<Void, Void, Void> {

        private int id;

        public AsyncDeleteHistory(int id){
            this.id = id;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL endpoint_uri = new URL(endpoint+"api?apiFunctionName=deleteHistory&apiFunctionParams={\"id\":" + id + "}");
                System.out.println(endpoint_uri.getHost());
                System.out.println(endpoint_uri.getPath());
                System.out.println(endpoint_uri.getPort());
                System.out.println(endpoint_uri.getProtocol());
                HttpURLConnection con = (HttpURLConnection) endpoint_uri.openConnection();
                con.setRequestMethod("GET");

                System.out.println(con.getResponseCode());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
