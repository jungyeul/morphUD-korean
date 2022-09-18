# Yet Another Format of Universal Dependencies for Korean (Accepted to COLING 2020)

Yige Chen<sup>1*</sup>, Eunkyul Leah Jo<sup>2*</sup>, Yundong Yao<sup>2*</sup>, KyungTae Lim<sup>3</sup>, Miikka Silfverberg<sup>2</sup>, Francis M. Tyers<sup>4</sup>, and Jungyeul Park<sup>2</sup>.
<sup>1</sup>The Chinese University of Hong Kong, Hong Kong, <sup>2</sup>The University of British Columbia, Canada, <sup>3</sup>Hanbat National University & TeddySum, South Korea, and <sup>4</sup>Indiana University, USA.
<sup>*</sup>Yige Chen, Eunkyul Leah Jo, and Yundong Yao contributed equally.


## Contents

<!-- - src: a script folder. -->
- data: a dataset folder and their parsing results including morphUD, +morphUD and wordUD (original). morphUD and +morphUD datasets include their parsing results in the form of wordUD (for "fair" comparison).
- word2morph: a folder containing the scripts that converts the word-based UD format to the morpheme-based proposed format. 
- morph2word: a folder containing the scripts that converts the morpheme-based proposed format back to the word-based UD format. 

## Results

|   | ko_gsd, wordUD | ko_gsd, morphUD | ko_kaist, wordUD | ko_kaist, morphUD |
|---|---|---|---|---|
| UDPipe | 70.90 | 77.01 | 77.01 | 81.80 |
| Stanza | 84.63 (±0.18) | 84.98 (±0.20) | 86.67 (±0.17) | 88.46 (±0.14) |

## References

```
@InProceedings{chen-jo-yao-lim-silfverberg-tyers-park:2022:COLING,
  author    = {Chen, Yige  and  Jo, Eunkyul Leah  and  Yao, Yundong  and  Lim, KyungTae  and  Silfverberg, Miikka  and  Tyers, Francis  and  Park, Jungyeul},
  title     = {Yet Another Format of Universal Dependencies for Korean},
  booktitle = {Proceedings of the 29th International Conference on Computational Linguistics},
  year      = {2022}
}
```
