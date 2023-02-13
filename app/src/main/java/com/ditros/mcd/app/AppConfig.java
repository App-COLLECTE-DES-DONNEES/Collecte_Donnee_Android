package com.ditros.mcd.app;

import android.content.Context;
import android.content.res.TypedArray;
import com.ditros.mcd.R;
import com.ditros.mcd.model.object;
import com.ditros.mcd.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppConfig {

    //mettre a jour les nouveaux articles ajoutés
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKE_TYPE_READ_ONLY_LABEL = "Read only access to a discounty account";
    public static final String IS_ACCOUNT = "is_adding_new_account";
    public static final String KEY_REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String BASE_URL = "https:///";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "full access";
    public static final String PARAM_USER_PASS = "USER_PASS";
    public static final String TABLE_TYPEROUTE = "road_type" ;
    public static String URL_PREFIX_KEYCLOAK = "";
    public static String Prefs_infos_login="infos_login";
    final  String LOGIN_URL = "auth/login";

    public static String clientId = "DataCollectBack";
    public static String clientSecret = "02cb2720-f6d4-4874-8fda-d39d2287463f";
    public static String POSTS_URL = "posts";
    public static final String AUTHTOKEN_TYPE = "full access";

    //Authorization
    static String AUTHORIZATION_CODE;
    private static final String GRANT_TYPE = "authorization_code";

    //Response
    public static  String Authcode;
    static String Tokentype;
    static String Refreshtoken;
    static Long Expiresin, ExpiryTime;

    public static List<object> getobjectData(Context ctx) {
        List<object> items = new ArrayList<>();
        //TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.object_images);
        String name_arr[] = ctx.getResources().getStringArray(R.array.object_names);

        for (int i = 0; i < name_arr.length; i++) {

            object obj = new object();

            obj.setId(String.valueOf(i));
            obj.setValue(name_arr[i]);
            obj.setDesc(name_arr[i]);

            items.add(obj);

        }
        Collections.shuffle(items);
        return items;
    }

}

