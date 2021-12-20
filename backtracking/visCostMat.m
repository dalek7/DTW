close all;

%% DTW backtracking
A = load('dtwout/match_w100_30.txt');
[wpg1, wpgz1]	= dtwbacktracking(A);

figure();
surf(A);
hold on
plot3(wpg1(:,1), wpg1(:,2), wpgz1+0.1, 'r-', 'LineWidth', 2);