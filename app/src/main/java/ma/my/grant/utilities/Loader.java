package ma.my.grant.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.util.ExtProperties;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;

public class Loader extends FileResourceLoader {
    private Context context;

    public void commonInit(RuntimeServices rs, ExtProperties configuration) {
        super.commonInit(rs, configuration);
        this.context = (Context) rs.getProperty("context");
    }

    public long getLastModified(Resource resource) {
        return resource.getLastModified();
    }

    @Override
    public Reader getResourceReader(String templateName, String encoding) throws ResourceNotFoundException {
        AssetManager assetManager = context.getResources().getAssets();
        try {
            return buildReader(assetManager.open(templateName),encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSourceModified(Resource resource) {
        return resource.isSourceModified();
    }

    public boolean resourceExists(String templateName) {
        return true;
    }
}
