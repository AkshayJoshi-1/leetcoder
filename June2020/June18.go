package main

import (
	"fmt"
)

func main() {
	fmt.Print(hIndex([]int{11, 15}))
}

func hIndex(citations []int) int {
	var totalCitations = len(citations)

	if totalCitations == 0 {
		return 0
	}

	if totalCitations == 1 && citations[0] > 0 {
		return 1 	
	} else if totalCitations == 1 && citations[0] == 0 {
		return 0
	}

	var minPoint = 0
	var maxPoint = totalCitations - 1

	for minPoint < maxPoint {
		var midPoint = minPoint + (maxPoint-minPoint+1)/2
		if citations[midPoint] > totalCitations-midPoint {
			maxPoint = midPoint - 1
		} else {
			minPoint = midPoint
		}
	}

	if citations[maxPoint] > totalCitations-maxPoint {
		return totalCitations
	}

	if citations[maxPoint] == totalCitations-maxPoint {
		return totalCitations - maxPoint
	} else {
		return totalCitations - maxPoint - 1
	}
}
