import pandas as pd
from statistics import mean, variance


data = pd.read_csv("AB_NYC_2019.csv")
print(
    f"mean = {mean(data['price'])}", f"variance = {variance(data['price'])}", sep="\n"
)
