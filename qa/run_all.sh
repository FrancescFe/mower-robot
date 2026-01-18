#!/bin/bash
for file in qa/*.txt; do
    echo "=== Running: $file ==="
    ./gradlew run -q < "$file"
    echo ""
done
