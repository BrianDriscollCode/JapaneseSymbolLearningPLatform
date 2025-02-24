import { defineStore } from "pinia";
import { chart } from "@/models/chart";

export const useLearnSettingsStore = defineStore('LearnSettings', {
    state: () => {
        return { 
            availableHiraganaRows: {
                A: JSON.parse(localStorage.getItem('availableHiraganaRowsA')) || true,
                K: JSON.parse(localStorage.getItem('availableHiraganaRowsK')) || true,
                S: JSON.parse(localStorage.getItem('availableHiraganaRowsS')) || false,
                T: JSON.parse(localStorage.getItem('availableHiraganaRowsT')) || false,
                N: JSON.parse(localStorage.getItem('availableHiraganaRowsN')) || false,
                H: JSON.parse(localStorage.getItem('availableHiraganaRowsH')) || false,
                M: JSON.parse(localStorage.getItem('availableHiraganaRowsM')) || false,
                Y: JSON.parse(localStorage.getItem('availableHiraganaRowsY')) || false,
                R: JSON.parse(localStorage.getItem('availableHiraganaRowsR')) || false,
                W: JSON.parse(localStorage.getItem('availableHiraganaRowsW')) || false,
                NN: JSON.parse(localStorage.getItem('availableHiraganaRowsNN')) || false,
                G: JSON.parse(localStorage.getItem('availableHiraganaRowsG')) || false,
                Z: JSON.parse(localStorage.getItem('availableHiraganaRowsZ')) || false,
                D: JSON.parse(localStorage.getItem('availableHiraganaRowsD')) || false,
                B: JSON.parse(localStorage.getItem('availableHiraganaRowsB')) || false,
                P: JSON.parse(localStorage.getItem('availableHiraganaRowsP')) || false,
            },
            availableKatakanaRows: {
                A: JSON.parse(localStorage.getItem('availableKatakanaRowsA')) || false,
                K: JSON.parse(localStorage.getItem('availableKatakanaRowsK')) || false,
                S: JSON.parse(localStorage.getItem('availableKatakanaRowsS')) || false,
                T: JSON.parse(localStorage.getItem('availableKatakanaRowsT')) || false,
                N: JSON.parse(localStorage.getItem('availableKatakanaRowsN')) || false,
                H: JSON.parse(localStorage.getItem('availableKatakanaRowsH')) || false,
                M: JSON.parse(localStorage.getItem('availableKatakanaRowsM')) || false,
                Y: JSON.parse(localStorage.getItem('availableKatakanaRowsY')) || false,
                R: JSON.parse(localStorage.getItem('availableKatakanaRowsR')) || false,
                W: JSON.parse(localStorage.getItem('availableKatakanaRowsW')) || false,
                NN: JSON.parse(localStorage.getItem('availableKatakanaRowsNN')) || false,
                G: JSON.parse(localStorage.getItem('availableKatakanaRowsG')) || false,
                Z: JSON.parse(localStorage.getItem('availableKatakanaRowsZ')) || false,
                D: JSON.parse(localStorage.getItem('availableKatakanaRowsD')) || false,
                B: JSON.parse(localStorage.getItem('availableKatakanaRowsB')) || false,
                P: JSON.parse(localStorage.getItem('availableKatakanaRowsP')) || false,
            },
            numberOfQuestions: 10
        }
    },

    actions: {

        toggleHiraganaRow(row) {
            console.log(row);
            if (this.availableHiraganaRows[row] !== undefined) {
                console.log('Row status: ' + row + ' - ' + `${this.availableHiraganaRows[row]}`);
                this.availableHiraganaRows[row] = !this.availableHiraganaRows[row];
                console.log('Row status: ' + row + ' - ' + `${this.availableHiraganaRows[row]}`);
                console.log("Row Toggled: " + row);
     
                localStorage.setItem(`availableHiraganaRows${row}`, this.availableHiraganaRows[row]);
                console.log('LearnSettingsRow: ', `availableHiraganaRows${row}`, this.availableHiraganaRows[row]);
            }
        },
        toggleKatakanaRow(row) {
            console.log(row);
            if (this.availableKatakanaRows[row] !== undefined) {
                console.log('Row status: ' + row + ' - ' + `${this.availableKatakanaRows[row]}`);
                this.availableKatakanaRows[row] = !this.availableKatakanaRows[row];
                console.log('Row status: ' + row + ' - ' + `${this.availableKatakanaRows[row]}`);
                console.log("Row Toggled: " + row);
     
                localStorage.setItem(`availableKatakanaRows${row}`, this.availableKatakanaRows[row]);
                console.log('LearnSettingsRow: ', `availableKatakanaRows${row}`, this.availableKatakanaRows[row]);
            }
        },
        setRow(row, value) {
            if (this.availableRows[row] !== undefined) {
                this.availableRows[row] = value;

                localStorage.setItem(`availableHiraganarows${row}`, this.availableHiraganaRows[row]);
            }
        },
      
    }
});