package com.example.batchcustomwriterdemo.processor;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;

@Slf4j
public class CompositeProcessor implements ItemProcessor<CompositeModel,CompositeModel>
{

    @Override
    public CompositeModel process(CompositeModel item) throws Exception {
        log.info("CompositeProcessor");
        return item;
    }
}
