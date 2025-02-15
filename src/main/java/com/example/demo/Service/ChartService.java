package com.example.demo.Service;

import com.example.demo.model.HiraganaChart;
import com.example.demo.model.HiraganaRow;
import org.springframework.stereotype.Service;

@Service
public class ChartService {

    public HiraganaChart generateHiraganaChart()
    {
        HiraganaChart chart = new HiraganaChart();

        // A row
        HiraganaRow aRow = new HiraganaRow("A");
        aRow.addCharacter("あ", "a", "a");
        aRow.addCharacter("い", "i", "i");
        aRow.addCharacter("う", "u", "u");
        aRow.addCharacter("え", "e", "e");
        aRow.addCharacter("お", "o", "o");
        chart.addRow(aRow);

        // K row
        HiraganaRow kRow = new HiraganaRow("K");
        kRow.addCharacter("か", "ka", "a");
        kRow.addCharacter("き", "ki", "i");
        kRow.addCharacter("く", "ku", "u");
        kRow.addCharacter("け", "ke", "e");
        kRow.addCharacter("こ", "ko", "o");
        chart.addRow(kRow);

        // S row
        HiraganaRow sRow = new HiraganaRow("S");
        sRow.addCharacter("さ", "sa", "a");
        sRow.addCharacter("し", "shi", "i");
        sRow.addCharacter("す", "su", "u");
        sRow.addCharacter("せ", "se", "e");
        sRow.addCharacter("そ", "so", "o");
        chart.addRow(sRow);

        // T row
        HiraganaRow tRow = new HiraganaRow("T");
        tRow.addCharacter("た", "ta", "a");
        tRow.addCharacter("ち", "chi", "i");
        tRow.addCharacter("つ", "tsu", "u");
        tRow.addCharacter("て", "te", "e");
        tRow.addCharacter("と", "to", "o");
        chart.addRow(tRow);

        // N row
        HiraganaRow nRow = new HiraganaRow("N");
        nRow.addCharacter("な", "na", "a");
        nRow.addCharacter("に", "ni", "i");
        nRow.addCharacter("ぬ", "nu", "u");
        nRow.addCharacter("ね", "ne", "e");
        nRow.addCharacter("の", "no", "o");
        chart.addRow(nRow);

        // H row
        HiraganaRow hRow = new HiraganaRow("H");
        hRow.addCharacter("は", "ha", "a");
        hRow.addCharacter("ひ", "hi", "i");
        hRow.addCharacter("ふ", "fu", "u");
        hRow.addCharacter("へ", "he", "e");
        hRow.addCharacter("ほ", "ho", "o");
        chart.addRow(hRow);

        // M row
        HiraganaRow mRow = new HiraganaRow("M");
        mRow.addCharacter("ま", "ma", "a");
        mRow.addCharacter("み", "mi", "i");
        mRow.addCharacter("む", "mu", "u");
        mRow.addCharacter("め", "me", "e");
        mRow.addCharacter("も", "mo", "o");
        chart.addRow(mRow);

        // Y row
        HiraganaRow yRow = new HiraganaRow("Y");
        yRow.addCharacter("や", "ya", "a");
        yRow.addCharacter("ゆ", "yu", "u");
        yRow.addCharacter("よ", "yo", "o");
        chart.addRow(yRow);

        // R row
        HiraganaRow rRow = new HiraganaRow("R");
        rRow.addCharacter("ら", "ra", "a");
        rRow.addCharacter("り", "ri", "i");
        rRow.addCharacter("る", "ru", "u");
        rRow.addCharacter("れ", "re", "e");
        rRow.addCharacter("ろ", "ro", "o");
        chart.addRow(rRow);

        // W row
        HiraganaRow wRow = new HiraganaRow("W");
        wRow.addCharacter("わ", "wa", "a");
        wRow.addCharacter("を", "wo", "o");
        chart.addRow(wRow);

        // N standalone row
        HiraganaRow nStandaloneRow = new HiraganaRow("N");
        nStandaloneRow.addCharacter("ん", "n", "-");
        chart.addRow(nStandaloneRow);

        // G row (Dakuten)
        HiraganaRow gRow = new HiraganaRow("G");
        gRow.addCharacter("が", "ga", "a");
        gRow.addCharacter("ぎ", "gi", "i");
        gRow.addCharacter("ぐ", "gu", "u");
        gRow.addCharacter("げ", "ge", "e");
        gRow.addCharacter("ご", "go", "o");
        chart.addRow(gRow);

        // Z row (Dakuten)
        HiraganaRow zRow = new HiraganaRow("Z");
        zRow.addCharacter("ざ", "za", "a");
        zRow.addCharacter("じ", "ji", "i");
        zRow.addCharacter("ず", "zu", "u");
        zRow.addCharacter("ぜ", "ze", "e");
        zRow.addCharacter("ぞ", "zo", "o");
        chart.addRow(zRow);

        // D row (Dakuten)
        HiraganaRow dRow = new HiraganaRow("D");
        dRow.addCharacter("だ", "da", "a");
        dRow.addCharacter("ぢ", "ji", "i");
        dRow.addCharacter("づ", "zu", "u");
        dRow.addCharacter("で", "de", "e");
        dRow.addCharacter("ど", "do", "o");
        chart.addRow(dRow);

        // B row (Dakuten)
        HiraganaRow bRow = new HiraganaRow("B");
        bRow.addCharacter("ば", "ba", "a");
        bRow.addCharacter("び", "bi", "i");
        bRow.addCharacter("ぶ", "bu", "u");
        bRow.addCharacter("べ", "be", "e");cd
        bRow.addCharacter("ぼ", "bo", "o");
        chart.addRow(bRow);

        // P row (Handakuten)
        HiraganaRow pRow = new HiraganaRow("P");
        pRow.addCharacter("ぱ", "pa", "a");
        pRow.addCharacter("ぴ", "pi", "i");
        pRow.addCharacter("ぷ", "pu", "u");
        pRow.addCharacter("ぺ", "pe", "e");
        pRow.addCharacter("ぽ", "po", "o");
        chart.addRow(pRow);

        return chart;
    }


}
