#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

struct Meeting {
    int start;
    int end;
};

bool compare(Meeting a, Meeting b) {
    if (a.end == b.end) {
        return a.start < b.start;
    }
    return a.end < b.end;
}

int main() {
    int n;
    cin >> n;

    vector<Meeting> meetings(n);

    for (int i = 0; i < n; i++)
        cin >> meetings[i].start >> meetings[i].end;

    sort(meetings.begin(), meetings.end(), compare);

    int count = 1;
    int endTime = meetings[0].end;

    for (int i = 1; i < n; i++) {
        if (meetings[i].start >= endTime) {
            count++;
            endTime = meetings[i].end;
        }
    }

    cout << count << endl;

    return 0;
}