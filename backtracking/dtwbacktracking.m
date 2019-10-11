% Implemented based on http://nipunbatra.github.io/2014/07/dtw/
% Seung-Chan Kim


function [warppath Z]= dtwbacktracking(M)
% M - calculated cost matrix
%
% Usage
% [wp1 wpz]	= dtwbacktracking(M);
% surf(M);
% hold on;
% plot3(wp1(:,1), wp1(:,2), wpz+0.1, 'r-', 'LineWidth', 2);

	sz = size(M);
	szx = sz(1);
	szy = sz(2);


	p = [szy,szx];
		
	i = szx;
	k = szy;


	while i>1 && k>1

		if i==1
			k = k-1;
		elseif k==1
			i = i-1;
		else
			if M(i-1,k) == min([M(i-1, k-1), M(i-1, k), M(i, k-1)])
				i = i-1;
			elseif M(i, k-1) == min([M(i-1, k-1), M(i-1, k), M(i, k-1)])
				k = k-1;
			else	
				i = i-1;
				k = k-1;
			end
		end

		p1 = [k,i];

		p = [p; p1];


	end


% corresponding z-values
	z	= zeros(length(p), 1);

	for i=1:length(p)
		z(i)	= M(p(i,2), p(i,1));
	end

	warppath = p;
	Z	 = z;