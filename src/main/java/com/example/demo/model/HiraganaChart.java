package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class HiraganaChart {
    public List<HiraganaRow> rows;

    public HiraganaChart()
    {
        this.rows = new ArrayList<>();
    }

    public void addRow(HiraganaRow row)
    {
        rows.add(row);
    }

    public List<HiraganaRow> getRows() { return rows; }

    public HiraganaRow getRowByName(String rowName)
    {
        for (HiraganaRow row: rows)
        {
            if (row.getRowName().equals(rowName))
            {
                return row;
            }
        }

        return null;
    }
}
