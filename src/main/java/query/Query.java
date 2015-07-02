package query;

import org.apache.http.NameValuePair;
import java.util.List;

public interface Query {
    List<NameValuePair> getParameters();
}
