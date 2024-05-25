package jp.nlaboratory.mybatis.sample;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import java.io.InputStream;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

/**
 * Xml loader for unit testing
 */
public class XmlDataLoader extends AbstractDataSetLoader {

  @Override
  protected IDataSet createDataSet(Resource resource) throws Exception {
    FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
    builder.setColumnSensing(true);
    try (InputStream stream = resource.getInputStream()) {
      return builder.build(stream);
    }
  }
}
