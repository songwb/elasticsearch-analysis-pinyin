package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.analysis.PinyinConfig;

/**
 * Created by IntelliJ IDEA.
 * User: Medcl'
 * Date: 12-5-22
 * Time: 上午10:39
 */
public final class PinyinAnalyzerForMutiple extends Analyzer {

    private PinyinConfig config;

    public PinyinAnalyzerForMutiple(PinyinConfig config) {
        this.config=config;
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        return new TokenStreamComponents(new PinyinTokenizerForMultiple(config));
    }

}
