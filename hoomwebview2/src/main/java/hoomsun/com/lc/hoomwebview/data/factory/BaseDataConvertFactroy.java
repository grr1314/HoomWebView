package hoomsun.com.lc.hoomwebview.data.factory;

import com.google.gson.Gson;

/**
 * Created by hoomsun on 2018/4/11.
 */

public abstract class BaseDataConvertFactroy<T> extends ConvertInterface.ConvertFactory<T> {
    T model;
    public Gson getBaseGson()
    {
        return new Gson();
    }
}
