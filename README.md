# Genetic Algorithm

This is a genetic algorithm implemented in java.


About Genetic Algorithms
------------------------

Small population can quickly converge on a solution and large population can run out of time to evolve and converge on a solution.
It affects the balance between diversification and intensification when selecting parents with the best fitness, making it clear that those with less fitness are likely to be accepted.

<b>Selection types:</b>
* Truncated: selects between 10% to 50% of the most fit population;
* Decimation: calculates average fitness and discards every individual below. Good because the best individual is always selected, but it is bad because sometimes a discarded individual could have a relevant characteristic;
* Proportional to fitness: each individual has a probability of being accepted according to their fitness (their fitness divided by the sum of all fitness), this method decreases the diversity of the population, but even individuals with few advantageous characteristics have a chance of being accepted.
* Selection based on ranking: the ranking is given to each individual from the worst to the best, and through the formula: rank of the guy / rank of everyone, each guy has his space in the roulette, it's good because it doesn't affect diversity very much, but it is more complex
* Tournament selection: send two guys to fight for your fitness and whoever wins is chosen, it is easier to implement and is computationally efficient
* Selection by survival: the children generated substitute part of the father

Example
--------


![GitHub Logo](/software.png)
