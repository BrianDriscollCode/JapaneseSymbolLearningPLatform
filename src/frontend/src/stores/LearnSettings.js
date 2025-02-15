import { defineStore } from "pinia";
import { chart } from "@/models/chart";

export const useLearnSettingsStore = defineStore('LearnSettings', {
    state: () => {
        return { 
            hiraOrKata: chart.Hiragana,
            availableRows: {
                A: true,
                K: true,
                S: true,
                T: true,
                N: false,
                H: false,
                M: false,
                Y: false,
                R: false,
                W: false,
                NN: true,
                G: false,
                Z: false,
                D: false,
                B: false,
                P: false,
            },
            numberOfQuestions: 10
        }
    },

    actions: {
        setHiraOrKata(setting) 
        {
            this.hiraOrKata = setting; 
        },

        toggleRow(row) {
            if (this.availableRows[row] !== undefined) {
                this.availableRows[row] = !this.availableRows[row];
                console.log("Row Toggled:" + row);
                console.log("AvailableRows: ", this.availableRows)
            }
        },

        setRow(row, value) {
            if (this.availableRows[row] !== undefined) {
                this.availableRows[row] = value;
            }
        },
      
    }
});