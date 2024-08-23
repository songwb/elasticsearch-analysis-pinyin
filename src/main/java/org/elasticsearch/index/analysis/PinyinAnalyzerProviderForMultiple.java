package org.elasticsearch.index.analysis;

import org.elasticsearch.analysis.PinyinConfig;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

/**
 */
public class PinyinAnalyzerProviderForMultiple extends AbstractIndexAnalyzerProvider<PinyinAnalyzerForMutiple> {

    private final PinyinAnalyzerForMutiple analyzer;
    private PinyinConfig config;

    @Inject
    public PinyinAnalyzerProviderForMultiple(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings,name,settings);
        config=new PinyinConfig(settings);
        analyzer = new PinyinAnalyzerForMutiple(config);
    }

    @Override
    public PinyinAnalyzerForMutiple get() {
        return this.analyzer;
    }
}
