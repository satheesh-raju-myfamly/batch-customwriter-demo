package com.example.batchcustomwriterdemo.writer;

import com.example.batchcustomwriterdemo.model.CompositeModel;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;

public class CompositeWriter implements ItemWriter<CompositeModel>
{


    @Override
    public void write(Chunk<? extends CompositeModel> chunk) throws Exception {

    }
}
