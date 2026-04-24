import os
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

os.chdir(os.path.dirname(os.path.abspath(__file__)))

best   = pd.read_csv("merge_sort_sorted_1m.csv",   header=None)
worst  = pd.read_csv("merge_sort_alternating.csv", header=None)
random = pd.read_csv("merge_sort_random.csv",      header=None)

sizes = np.array([100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000])
x = sizes * np.log(sizes)

y_best   = best.mean(axis=1).values
y_worst  = worst.mean(axis=1).values
y_random = random.mean(axis=1).values

# fit a degree 1 (linear) polynomial to each dataset
best_fit   = np.polyfit(x, y_best,   1)
worst_fit  = np.polyfit(x, y_worst,  1)
random_fit = np.polyfit(x, y_random, 1)

# generate smooth x values for the line of best fit
x_line = np.linspace(x.min(), x.max(), 500)

# evaluate the polynomial at each x point
best_line   = np.polyval(best_fit,   x_line)
worst_line  = np.polyval(worst_fit,  x_line)
random_line = np.polyval(random_fit, x_line)

plt.figure(figsize=(8, 5))

# plot raw data points
plt.scatter(x, y_best,   color='orange', marker='^', zorder=3)
plt.scatter(x, y_worst,  color='blue',   marker='^', zorder=3)
plt.scatter(x, y_random, color='cyan',   marker='^', zorder=3)

# plot lines of best fit
plt.plot(x_line, best_line,   color='orange', label='best case result')
plt.plot(x_line, worst_line,  color='blue',   label='worst case result')
plt.plot(x_line, random_line, color='cyan',   label='random case')

plt.xlabel("n log(n) of input size n")
plt.ylabel("Time (ms)")
plt.title("Merge Sort")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("merge_sort_plot.png", dpi=150)
plt.show()