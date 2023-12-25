import pandas as pd

data = pd.read_csv("../SPFB.RTS-12.18_180901_181231.csv")

res = data[data['<DATE>'] == "19/12/18"]

for i in ["11", "10", "09"]:
    res = pd.concat([data[data['<DATE>'] == f"19/{i}/18"], res])

res.to_csv("addition_2_res.csv", index=None)